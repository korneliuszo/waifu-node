#!/usr/bin/env python3
"""
Parse Cargo.lock → BitBake .inc with one SRC_URI line per crate.

Top-level (direct) crates → uncommented SRC_URI + sha256sum.
Transitive crates → commented-out lines (shown as candidates).

Usage:
    python3 cargo_lock_to_bbinc.py [path/to/Cargo.lock] > output.inc
"""

import sys
import re


def parse_cargo_lock(text):
    """Parse Cargo.lock into a list of package dicts."""
    packages = []
    current = None
    in_deps_block = False
    current_deps_key = None

    for line in text.splitlines():
        line_stripped = line.strip()

        # New package header
        pkg_match = re.match(r'^\[\[package\]\]$', line_stripped)
        if pkg_match:
            if current and in_deps_block:
                in_deps_block = False
            if current:
                packages.append(current)
            current = {}
            in_deps_block = False
            continue

        if current is None:
            continue

        # Inside a multi-line array (dependencies, ...)
        if in_deps_block:
            if line_stripped == ']':
                in_deps_block = False
            else:
                item = line_stripped.strip(' ,')
                if item:
                    current.setdefault(current_deps_key, []).append(item)
            continue

        # Key = value
        kv = re.match(r'^(\w+)\s*=\s*"(.*)"$', line_stripped)
        if kv:
            current[kv.group(1)] = kv.group(2)
            continue

        # Key = [single-line array]
        kv_arr_inline = re.match(r'^(\w+)\s*=\s*\[(.*)\]$', line_stripped)
        if kv_arr_inline:
            current[kv_arr_inline.group(1)] = [
                x.strip().strip('"') for x in kv_arr_inline.group(2).split(',')
            ]
            continue

        # Key = [start of multi-line array]
        kv_arr_open = re.match(r'^(\w+)\s*=\s*\[\s*$', line_stripped)
        if kv_arr_open:
            current_deps_key = kv_arr_open.group(1)
            current.setdefault(current_deps_key, [])
            in_deps_block = True
            continue

    if current:
        packages.append(current)

    return packages


def name_to_bb_variable(name):
    """Convert crate name to a safe BitBake variable name."""
    return name


def generate_inc(packages):
    """Generate BitBake .inc content."""
    packages.sort(key=lambda p: p.get('name', ''))

    lines = []
    lines.append('# Auto-generated from Cargo.lock')
    lines.append('# Direct crates  : uncommented (active in SRC_URI)')
    lines.append('# Transitive deps : commented (available as candidates)')
    lines.append('')

    # --- Direct-package detection ---
    # A package is "direct" (top-level in Cargo.toml) if it has
    # no dependencies key at all in the lockfile.
    # Packages with dependencies = [...] are transitive.
    direct_names = set()
    for p in packages:
        if 'dependencies' not in p:
            direct_names.add(p['name'])

    prev_direct = True
    for p in packages:
        name = p['name']
        version = p['version']
        checksum = p.get('checksum', '0' * 64)
        source = p.get('source', '')
        is_direct = name in direct_names

        # Visual separator between direct and transitive blocks
        if is_direct != prev_direct:
            lines.append('')
        prev_direct = is_direct

        bb_name = name_to_bb_variable(name)
        prefix = '' if is_direct else '#'

        if source=="":
            continue
        
        # Comment describing the package
        lines.append(f'# {prefix} # {name} ({version}) [{source}]')

        if checksum == '0'*64:
            repo = source.split("://")[1].split("#")[0]

            lines.append(f'SRC_URI += "git://{repo.replace("?",";")};protocol=https;nobranch=1;name={name};destsuffix={name}-{version}/ "')
            lines.append(f'EXTRA_OECARGO_PATHS += " ${{UNPACKDIR}}/{name}-{version}/ "')
            #lines.append(f'SRCREV_{name} = "{repo.split("?")[1]}"')
            continue

        # sha256sum line (self-contained, closes quote)
        lines.append(
            f'SRC_URI[{bb_name}-{version}.sha256sum] = "{checksum}"'
        )
            

        # SRC_URI line (self-contained, closes quote)
        lines.append(f'SRC_URI += "crate://crates.io/{name}/{version} "')

        # sha256sum line (self-contained, closes quote)
        lines.append(f'SRC_URI[{bb_name}-{version}.sha256sum] = "{checksum}"')
    lines.append('')

    return '\n'.join(lines)


def main():
    path = sys.argv[1] if len(sys.argv) > 1 else 'Cargo.lock'

    try:
        with open(path, 'r') as f:
            text = f.read()
    except FileNotFoundError:
        print(f"Error: {path} not found", file=sys.stderr)
        sys.exit(1)

    packages = parse_cargo_lock(text)
    print(generate_inc(packages))


if __name__ == '__main__':
    main()
