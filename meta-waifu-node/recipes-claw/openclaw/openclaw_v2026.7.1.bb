LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://LICENSE;md5=dc09fa4d084b850061c1dd086ca06f89"

SRC_URI = " \
    https://registry.npmjs.org/openclaw/-/openclaw-2026.7.1.tgz;unpack=0 \
"
SRC_URI[sha256sum] = "67ad539d9915efb63d5f294beeb9290b7172d23c92d8052110a9c8355f783458"

SRC_URI[types/retry0.12.0.sha256sum] = "df61c6d197aadc6ab17b489103533bbb0223413558d4222411c162155c30b428"

S = "${UNPACKDIR}/package"

inherit npm


require openclaw_deps.inc

INSANE_QA = "already-stripped"

DEPENDS += " bash-native python3-native nodejs-native nodejs "

RDEPENDS:${PN} += " libstdc++ libgcc bash "

#PACKAGES:remove = "${PN}-dev"

do_install:append() {
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/sqlite-vec-darwin-arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/sqlite-vec-darwin-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/sqlite-vec-linux-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/sqlite-vec-windows-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/tree-sitter-bash/prebuilds/linux-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/tree-sitter-bash/prebuilds/darwin-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/tree-sitter-bash/prebuilds/darwin-arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/tree-sitter-bash/prebuilds/win32-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/tree-sitter-bash/prebuilds/win32-arm64

}

INSANE_SKIP:${PN}-dbg += "buildpaths"
FILES:${PN}-dbg += "/usr/lib/node_modules/openclaw/node_modules/tree-sitter-bash/node-addon-api/*.mk"

do_unpack:append() {
    import tarfile
    unpackdir = d.getVar('UNPACKDIR')
    f=tarfile.open(unpackdir+"/openclaw-2026.7.1.tgz")
    f.extractall(path=unpackdir, filter="data")
}

python do_configure() {
    """
    Step one: configure the npm cache and the main npm package

    Every dependencies have been fetched and patched in the source directory.
    They have to be packed (this remove unneeded files) and added to the npm
    cache to be available for the next step.

    The main package and its associated manifest file and shrinkwrap file have
    to be configured to take into account these cached dependencies.
    """
    import base64
    import copy
    import json
    import re
    import shlex
    import stat
    import tempfile
    from bb.progress import OutOfProgressHandler
    from oe.npm_registry import NpmRegistry

    registry = NpmRegistry(d.getVar('NPM_REGISTRY'), d.getVar('NPM_CACHE'))

    npm_deps = d.getVar('NPM_DEPS') + d.getVar('DEV_DEPS')
    unpackdir = d.getVar('UNPACKDIR')

    for dep in npm_deps.split():
        fields=dep.split("::")
        registry.add_pkg(unpackdir+"/"+fields[2], {"name":fields[1],"version":fields[5]})
    registry.add_pkg(unpackdir+"/openclaw-2026.7.1.tgz", {"name":"openclaw","version":"2026.7.1"})
    registry.add_pkg(unpackdir+"/retry-0.12.0.tgz", {"name":"@types/retry","version":"0.12.0"})

}


python do_compile() {
    import shlex
    import tempfile   
    import os
    current_env = os.environ.copy()
    current_env["NODE_ENV"] = "production"
    current_env["TARGET_CPU"] = "arm64"
    current_env["TARGET_PLATFORM"] = "linux"
    current_env["NPM_CONFIG_AUDIT"] = "false"
    current_env["NPM_CONFIG_FUND"] = "false"
    current_env["NPM_CONFIG_NODEDIR"] = d.getVar("NPM_NODEDIR")

    #bb.utils.mkdirhier(d.getVar('WORKDIR')+"/build/")
    #bb.utils.mkdirhier(d.getVar('WORKDIR')+"/build/node_modules")
    bb.utils.remove(d.getVar("NPM_BUILD"), recurse=True)   
    bb.utils.mkdirhier(d.getVar('NPM_BUILD'))
    unpackdir = d.getVar('UNPACKDIR')
    npm_deps = d.getVar('NPM_DEPS')
    with tempfile.TemporaryDirectory() as tmpdir:
        #(tarball, _) = npm_pack(None, d.getVar("NPM_PACKAGE"), tmpdir)
        cmd = ["npm", "cache", "verify", "--offline", "--cache="+d.getVar('NPM_CACHE')]
        bb.process.run(cmd,env=current_env,cwd=tmpdir)
        #cmd = ["npm", "--offline",
        #    "--cache="+d.getVar('NPM_CACHE')+"/",
        #    "install", unpackdir+"/node-addon-api-8.9.0.tgz",
        #    "--cpu=arm64",
        #    "--arch=linux-arm64",
        #    "--target_arch=linux-arm64",
        #    "--release=true",
        #    "--legacy-peer-deps",
        #    #"--workspace="+d.getVar('S'),
        #    "--python="+d.getVar('PYTHON'),
        #    "--platform=linux",
        #    "--build-from-source",
        #    "--prefix="+d.getVar('NPM_BUILD'),
        #    "--global",
        #    ] + shlex.split(d.getVar("EXTRA_OENPM") or "")
        #bb.process.run(cmd,env=current_env,cwd=d.getVar('S'))
        cmd = ["npm", "--offline",
            "--cache="+d.getVar('NPM_CACHE')+"/",
            "install", "openclaw@2026.7.1",
            "--omit=dev",
            "--cpu=arm64",
            "--arch=linux-arm64",
            "--target_arch=linux-arm64",
            #"--workspace="+d.getVar('S'),
            "--python="+d.getVar('PYTHON'),
            "--platform=linux",
            "--prefix="+d.getVar('NPM_BUILD'),
            "--global",
            ] + shlex.split(d.getVar("EXTRA_OENPM") or "")
        bb.process.run(cmd,env=current_env,cwd=d.getVar('NPM_BUILD'))
        cmd = ["npm", "--offline",
            "--cache="+d.getVar('NPM_CACHE')+"/",
            "install",
            "--omit=dev",
            "--cpu=arm64",
            "--arch=linux-arm64",
            "--target_arch=linux-arm64",
            #"--workspace="+d.getVar('S'),
            "--python="+d.getVar('PYTHON'),
            "--platform=linux",
            ] + shlex.split(d.getVar("EXTRA_OENPM") or "")
        bb.process.run(cmd,env=current_env,cwd=d.getVar('NPM_BUILD')+"/lib/node_modules/openclaw/")

}
