#!/usr/bin/env python3
"""
gen-openclaw-deps.py — Generate NPM_DEPS for openclaw_deps.inc from
npm-shrinkwrap.json (source of truth for *which* packages and *filenames*)
and package.json (source of truth for *dependency types*).

Uses the shrinkwrap `resolved` URLs directly.

Package types from package.json:
  - dependencies → prod
  - devDependencies → dev
  - optionalDependencies → opt

A dep's type is the highest-priority section it appears in.
Dev-only deps not in shrinkwrap are reported (not fetched).

Output: full NPM_DEPS block in deps.inc format with https:// URLs.
Runs from recipes-claw/openclaw/ directory.

Usage:
  python3 gen-openclaw-deps.py > openclaw_deps.inc.new
  python3 gen-openclaw-deps.py --diff  # also diff against current
  python3 gen-openclaw-deps.py --write  # overwrite in place
"""

import json
import sys
import re
from pathlib import Path

SCRIPT_DIR = Path(__file__).resolve().parent #TODO: parametrize - for now scriptdir is workdir
SHRINKWRAP = SCRIPT_DIR / "package-lock.json"
PACKAGE_JSON = SCRIPT_DIR / "package.json"


def load_shrinkwrap():
    with open(SHRINKWRAP) as f:
        return json.load(f)


def load_package_json():
    """Load package.json and build a type map for all dependency keys.

    Priority: production > dev > optional (production wins if dep appears
    in multiple sections, e.g. @types/node is both prod and dev).

    Returns dict: { dep_name: "prod" | "dev" | "opt" }
    """
    with open(PACKAGE_JSON) as f:
        pj = json.load(f)

    pkg_map = {}
    # Lower priority first; higher overwrites
    for name in pj.get("optionalDependencies", {}):
        pkg_map[name] = "opt"
    for name in pj.get("devDependencies", {}):
        pkg_map[name] = "dev"
    for name in pj.get("dependencies", {}):
        pkg_map[name] = "prod"

    return pkg_map


def extract_filename_from_url(url):
    """Extract the .tgz filename from a registry URL.

    Pattern: https://registry.npmjs.org/<name>/-/filename.tgz
    or:      https://registry.npmjs.org/<scoped>/-/filename.tgz
    """
    if not url:
        return None
    parts = url.split("/")
    # Last path segment before the filename
    if parts[-1].endswith(".tgz"):
        return parts[-1]
    # Fallback: last part
    return None

def extract_packagename_from_url(url):
    """Extract the .tgz filename from a registry URL.

    Pattern: https://registry.npmjs.org/<name>/-/filename.tgz
    or:      https://registry.npmjs.org/<scoped>/-/filename.tgz
    """
    if not url:
        return None
    # Last path segment before the filename
    parts = url.split("/")
    return "/".join(parts[3:-2])


def extract_http_url(url):
    """Ensure the URL is a proper https:// registry URL.

    Returns the resolved URL as-is if already https://.
    """
    if url and (url.startswith("https://") or url.startswith("http://")):
        return url
    return None



def _dev_only_filename(name, version):
    """Construct the npm registry filename for a dev-only dep.

    Scoped: @scope/name → name-version.tgz
    Plain:  name → name-version.tgz
    """
    local = name.split("/")[-1]
    return f"{local}-{version}.tgz"


def _dev_only_url(name, filename):
    """Construct the npm registry URL for a dev-only dep.

    Pattern: https://registry.npmjs.org/<name>/-/filename.tgz
    """
    return f"https://registry.npmjs.org/{name}/-/{filename}"


def _fetch_sha512(url):
    """Download a tgz from npm registry and compute sha512 integrity.

    Returns the sha512 digest as base64 (matching npm registry format),
    like sha512-NT2KqphUJ3w6Eks...==
    """
    import base64
    import hashlib
    import urllib.request
    try:
        with urllib.request.urlopen(url, timeout=300) as resp:
            data = resp.read()
        digest = hashlib.sha512(data).digest()
        b64 = base64.b64encode(digest).decode('ascii')
        return f"sha512-{b64}"
    except Exception:
        return ""


def build_deps(shrinkwrap, pkg_map=None):
    """Build deps from shrinkwrap, annotated with types from package.json.

    Returns: (deps_list, dev_only_list)
        deps_list: all shrinkwrap entries with type info (no type suffix on key)
        dev_only_list: devDependencies not in shrinkwrap, with constructed URLs
    """
    shrinkwrap_pkgs = shrinkwrap.get("packages", {})
    pkg_map = pkg_map or {}
       
    deps = []
    dev_only = []

    # Add all shrinkwrap deps — full path as key, bare name for SRC_URI
    for full_path in sorted(shrinkwrap_pkgs.keys()):
        info = shrinkwrap_pkgs[full_path]
        name = full_path[13:]
        if name == "":
            continue
        if info.get("inBundle",False):
            continue
        version = info["version"]
        filename = _dev_only_filename(name, version)
        url = info["resolved"]
        integrity = info["integrity"]
        deps.append((name, version, "dev", filename, url, integrity))

    # Find dev-only deps (in devDependencies but NOT in shrinkwrap)
    # Build full URL from registry convention and fetch sha512 integrity
    dev_deps_section = {}
    with open(PACKAGE_JSON) as f:
        jsn = json.load(f)
        dev_deps_section = {**jsn.get("dependencies", {}),
                            **jsn.get("devDependencies", {}),
                            **jsn.get("optionalDependencies", {})};
    for name, version in sorted(dev_deps_section.items()):
        name_key = f"node_modules/{name}"
        if name_key in shrinkwrap_pkgs and shrinkwrap_pkgs[name_key]["version"] == version:
            continue
        filename = _dev_only_filename(name, version)
        url = _dev_only_url(name, filename)
        integrity = _fetch_sha512(url)
        dev_only.append((name, version, "dev", filename, url, integrity))

    return deps, dev_only


def format_deps(deps, dev_only=None):
    """Format deps as the full openclaw_deps.inc file."""
    lines = ['NPM_DEPS += " \\']
    for name, version, dep_type, filename, url, integrity in deps:
        dep_key = f"{name}@{version}::{name}::{filename}::{url}::{integrity}::{version}"
        lines.append(f"  {dep_key} \\")
    lines.append('"')
    lines.append('')

    # DEV_DEPS — same structured format as NPM_DEPS, with real shasums.
    # Merged into NPM_DEPS via "${DEV_DEPS}" so a single python block handles both.
    if dev_only:
        lines.append('DEV_DEPS += " \\')
        for name, version, dep_type, filename, url, integrity in dev_only:
            dep_key = f"{name}@{version}::{name}::{filename}::{url}::{integrity}::{version}"
            lines.append(f"  {dep_key} \\")
        lines.append('"')
        lines.append('')
    else:
        lines.append('DEV_DEPS += " "')

    lines.append('python () {')
    lines.append('    import base64')
    lines.append('    npm_deps = d.getVar(\'NPM_DEPS\') + d.getVar(\'DEV_DEPS\')')
    lines.append('    for dep in npm_deps.split():')
    lines.append('        fields=dep.split("::")')
    lines.append('        d.appendVar("SRC_URI", " "+fields[3]+";unpack=0;name="+fields[0]+" ")')
    lines.append('        # Only set sha sum for entries that have integrity (shrinkwrap entries)')
    lines.append('        integrity = fields[4]')
    lines.append('        if integrity:  # non-empty means real sha from shrinkwrap')
    lines.append('            sum=integrity.split("-")')
    lines.append('            d.setVarFlag("SRC_URI",fields[0]+"."+sum[0]+"sum",base64.b64decode(sum[1]).hex())')
    lines.append('}')
    lines.append('')
    return "\n".join(lines) + "\n"


def main():
    import argparse
    parser = argparse.ArgumentParser(description="Generate openclaw_deps.inc NPM_DEPS")
    parser.add_argument("--write", action="store_true", help="Write to openclaw_deps.inc")
    parser.add_argument("--diff", action="store_true", help="Diff against current file")
    args = parser.parse_args()

    shrinkwrap = load_shrinkwrap()
    pkg_map = load_package_json()
    deps, dev_only = build_deps(shrinkwrap, pkg_map)

    output = format_deps(deps, dev_only)

    if args.write or not sys.stdout.isatty():
        if args.write:
            Path("openclaw_deps.inc").write_text(output + "\n")
            print(f"# Written openclaw_deps.inc ({len(deps)} deps)", file=sys.stderr)
        else:
            print(output)

    # Summary to stderr
    total = len(deps)
    print(f"# {total} deps", file=sys.stderr)
    if dev_only:
        print(f"# {len(dev_only)} dev-only deps (not in shrinkwrap, full URLs in DEV_DEPS)", file=sys.stderr)
    print(f"# shrinkwrap: {SHRINKWRAP} ({len(shrinkwrap.get('packages', {}))} pkg entries)", file=sys.stderr)
    print(f"# package.json: {PACKAGE_JSON} (deps: {len(pkg_map)} entries)", file=sys.stderr)

    if args.diff:
        current = Path("openclaw_deps.inc").read_text()
        # Extract dep filenames from current file
        current_deps = []
        for line in current.split("\n"):
            line = line.strip().rstrip("\\")
            if ":" in line and line and line != "NPM_DEPS" and not line.startswith("DEV_DEPS"):
                current_deps.append(line)
        print(f"# Current file has {len(current_deps)} deps", file=sys.stderr)


if __name__ == "__main__":
    main()
