# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=048160ce07d177648e699b6f41a72b9a \
                    file://third_party/f2reduce/LICENCE.txt;md5=1077d374bd619bc68bfe2023d1fabf76"

DEPENDS = "python3-pybind11-native"
SRC_URI = "git://github.com/triton-lang/triton;protocol=https;nobranch=1;branch=master;name=triton;subdir=triton"
SRC_URI += "file://fix-cross.patch;patchdir=${S}"

S = "${UNPACKDIR}/triton"

SRC_URI += "git://github.com/google/googletest;protocol=https;nobranch=1;rev=52eb8108c5bdec04579160ae17225d66034bd723;name=gtest;subdir=googletest"
SRC_URI += "git://github.com/korneliuszo/llvm-project;protocol=https;nobranch=1;rev=1f126a6dea50d185c0781743a667390037ae88bd;name=llvm;subdir=llvm"

SRCREV_FORMAT = "triton"

# Modify these as desired
PV = "3.7.1"
SRCREV_triton = "f797708c0626e5f9840ca5b0a98790e2c7cb09ad"

inherit perlnative pkgconfig cmake cuda python_setuptools_build_meta

DEPENDS += "cuda-cudart cmake-native ninja-native zstd"

EXTRA_OECMAKE:append = " \
        -DGOOGLETEST_DIR=${UNPACKDIR}/googletest \
        -DLLVM_DIR=${WORKDIR}/llvm-install/lib/cmake/llvm/ \
        -DLLVM_NATIVE_TOOL_DIR=${WORKDIR}/llvm-install/NATIVE/bin \
"
#        -DLLVM_EXTERNAL_LIT=${LLVM_BUILD_PATH}/bib/llvm-lit \
#"
#
#

LLVM_BUILD_PATH = "${WORKDIR}/llvm-build"

do_compile_llvm(){
        #export CXX="${BUILD_CXX}"
        #export LD="${BUILD_LD}"
        #cmake -G Ninja -DCMAKE_BUILD_TYPE=Release -DLLVM_HOST_TRIPLE=aarch64-oe-linux-gnu -DLLVM_ENABLE_PROJECTS="mlir;llvm;lld" -DLLVM_TARGETS_TO_BUILD="AArch64;NVPTX;AMDGPU;X86" -DLLVM_BUILD_UTILS=ON -DLLVM_INCLUDE_TESTS=ON -DCMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain-native.cmake -DLLVM_ENABLE_ZSTD=OFF -DMLIR_ENABLE_BINDINGS_PYTHON=OFF -B ${WORKDIR}/llvm-stage1 -S ${UNPACKDIR}/llvm/llvm/
        #cmake --build ${WORKDIR}/llvm-stage1
        export CXX="${CXX}"
        export LD="${LD}"
        cmake -G Ninja -DCMAKE_BUILD_TYPE=Release -DCROSS_TOOLCHAIN_FLAGS_NATIVE='-DCMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain-native.cmake' -DLLVM_ENABLE_PROJECTS="mlir;llvm;lld" -DLLVM_TARGETS_TO_BUILD="AArch64;NVPTX;AMDGPU;X86" --toolchain=${WORKDIR}/toolchain.cmake -DLLVM_ENABLE_ZSTD=OFF -DMLIR_ENABLE_BINDINGS_PYTHON=OFF -DCMAKE_CROSSCOMPILING=ON -B ${WORKDIR}/llvm-install/ -S ${UNPACKDIR}/llvm/llvm/
        cmake --build ${WORKDIR}/llvm-install
}

addtask do_compile_llvm after do_configure before do_compile 

do_compile(){
        export TRITON_BUILD_WITH_CLANG_LLD=0
        export TRITON_BUILD_WITH_CCACHE=0
        export TRITON_APPEND_CMAKE_ARGS='${OECMAKE_ARGS} ${EXTRA_OECMAKE}'
        export TRITON_OFFLINE_BUILD=1
       export TRITON_BUILD_PROTON=OFF
        export LLVM_SYSPATH=${WORKDIR}/llvm-install
        export JSON_SYSPATH=${STAGING_DIR_HOST}/usr/include/jsoncpp
       export TRITON_HOME=${WORKDIR}/triton-home
       export PYBIND11_SYSPATH=${STAGING_DIR_HOST}/usr
       export PYTHON_SYSPATH=${STAGING_DIR_HOST}/usr/include/python3.14/
        python_pep517_do_compile
}

INSANE_SKIP:${PN} += " buildpaths rpaths"
INSANE_SKIP:${PN}-src += " buildpaths"
INSANE_SKIP:${PN}-dbg += " buildpaths"
