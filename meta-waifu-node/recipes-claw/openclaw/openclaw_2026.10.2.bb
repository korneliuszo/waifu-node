LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://LICENSE;md5=1d9fea5de9f3e5105623d22397670584"

SRC_URI = " \
    file://package-lock.json \
"

S = "${UNPACKDIR}/package"

inherit npm

require openclaw_deps.inc

INSANE_QA = "already-stripped"

DEPENDS += " bash-native python3-native nodejs-native nodejs "

RDEPENDS:${PN} += " libstdc++ libgcc bash libxkbcommon libxcb libx11 libxi "

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
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-linux-arm
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-android-arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-linux-ppc64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-linux-riscv64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-linux-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-linux-arm64/musl_arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-freebsd-ia32
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-freebsd-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-openbsd-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-openbsd-ia32
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-android-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-linux-ia32
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-freebsd-arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-linux-loong64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@earendil-works/pi-tui/native/linux/prebuilds/linux-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/aix-ppc64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/android-arm
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/android-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/darwin-arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/darwin-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/openharmony-arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/win32-arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/win32-ia32
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/win32-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/opeVnbsd-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/openbsd-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/sunos-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/freebsd-arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/linux-ia32
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/linux-s390x
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/openbsd-arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/openbsd-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/freebsd-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/linux-ppc64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/linux-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/linux-riscv64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/linux-arm
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/android-arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/netbsd-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/linux-mips64el
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/linux-loong64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@esbuild/netbsd-arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@trycua/cua-driver-linux-x64-gnu
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@openclaw/fs-safe-linux-x64-musl
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@openclaw/fs-safe-linux-x64-gnu
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@openclaw/fs-safe-linux-arm64-musl
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@lydell/node-pty-linux-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@ubjs/node-linux-x64-gnu
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@ubjs/node-linux-x64-musl
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-openbsd-arm64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@koromix/koffi-openbsd-x64
    rm -rf ${D}/usr/lib/node_modules/openclaw/node_modules/\@ubjs/node-linux-arm64-musl

}

INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN}-dbg += "buildpaths"
FILES:${PN}-dbg += "/usr/lib/node_modules/openclaw/node_modules/tree-sitter-bash/node-addon-api/*.mk"

do_unpack:append() {
    import tarfile
    unpackdir = d.getVar('UNPACKDIR')
    f=tarfile.open(unpackdir+"/openclaw-2026.10.2.tgz")
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
    registry.add_pkg(unpackdir+"/openclaw-2026.10.2.tgz", {"name":"openclaw","version":"2026.10.2"})

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
    #current_env["NPM_CONFIG_NODEDIR"] = d.getVar("NPM_NODEDIR")

    bb.utils.mkdirhier(d.getVar('WORKDIR')+"/build/")
    #bb.utils.copyfile(d.getVar('UNPACKDIR')+"/package.json",d.getVar('WORKDIR')+"/build/package.json")
    #bb.utils.copyfile(d.getVar('UNPACKDIR')+"/package-lock.json",d.getVar('WORKDIR')+"/build/package-lock.json")
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
            "--before=2999-12-31",
            "--cache="+d.getVar('NPM_CACHE')+"/",
            "install", "openclaw",
            "--foreground-scripts=true",
            #"--omit=dev",
            "--include=prod","--include=optional","--include=peer",
            "--cpu=arm64",
            "--os=linux",
            "--libc=gnu",
            #"-g",
            #"--workspace="+d.getVar('S'),
            #"--prefix="+d.getVar('NPM_BUILD'),
            ] + shlex.split(d.getVar("EXTRA_OENPM") or "")
        bb.process.run(cmd,env=current_env,cwd=d.getVar('WORKDIR')+"/build/")
        bb.utils.copyfile(d.getVar('UNPACKDIR')+"/package-lock.json",d.getVar('WORKDIR')+"/build/node_modules/openclaw/package-lock.json")
        #bb.utils.copyfile(d.getVar('UNPACKDIR')+"/package-lock.json",d.getVar('WORKDIR')+"/build/node_modules/.package-lock.json")
        #bb.utils.copyfile(d.getVar('UNPACKDIR')+"/package-lock.json",d.getVar('WORKDIR')+"/build/package-lock.json")
        cmd = ["npm", "--offline",
            "--before=2999-12-31",
            "--cache="+d.getVar('NPM_CACHE')+"/",
            "ci",
            "--ignore-scripts=true",
            "--install-strategy=linked",
            "--omit=dev",
            "--cpu=arm64",
            "--os=linux",
            "--libc=gnu",
            "--install-links=true",
            #"-g",
            #"--workspace="+d.getVar('S'),
            #"--prefix="+d.getVar('NPM_BUILD'),
            ] + shlex.split(d.getVar("EXTRA_OENPM") or "")
        bb.process.run(cmd,env=current_env,cwd=d.getVar('WORKDIR')+"/build/")
        bb.utils.copyfile(d.getVar('UNPACKDIR')+"/package-lock.json",d.getVar('WORKDIR')+"/build/node_modules/openclaw/package-lock.json")
        cmd = ["npm", "--offline"
            "--before=2999-12-31",
            "--cache="+d.getVar('NPM_CACHE')+"/",
            "install", "./node_modules/openclaw/",
            "--ignore-scripts=true",
            "--allow-scripts=openclaw",
            "--install-strategy=hoisted",
            "--omit=dev",
            "--cpu=arm64",
            "--os=linux",
            "--libc=gnu",
            "-g", "--install-links=true",
            "--prefix="+d.getVar('NPM_BUILD'),
            #"--workspace="+d.getVar('S'),
            ] + shlex.split(d.getVar("EXTRA_OENPM") or "")
        bb.process.run(cmd,env=current_env,cwd=d.getVar('WORKDIR')+"/build/")

}
