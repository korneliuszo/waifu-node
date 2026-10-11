#
# SPDX-FileCopyrightText: <text>Copyright 2026 Arm Limited and/or its
# affiliates <open-source-office@...></text>
#
# SPDX-License-Identifier: MIT

SUMMARY = "MLIR compiler infrastructure"
DESCRIPTION = "MLIR is a reusable and extensible compiler infrastructure from the LLVM project."
HOMEPAGE = "https://mlir.llvm.org"
SECTION = "devel"

require recipes-devtools/clang/common-clang.inc
require recipes-devtools/clang/common-source.inc

LICENSE = "Apache-2.0-with-LLVM-exception"
LIC_FILES_CHKSUM = "file://mlir/LICENSE.TXT;md5=7ef1911355f6e321fa0d7971689b7a67"

inherit cmake pkgconfig

DEPENDS = "llvm"
DEPENDS:append:class-target = " llvm-mlir-native"
DEPENDS:append:class-nativesdk = " llvm-mlir-native"

OECMAKE_SOURCEPATH = "${S}/mlir"

PACKAGECONFIG ??= "shared-libs"
PACKAGECONFIG[shared-libs] = "\
    -DLLVM_BUILD_LLVM_DYLIB=ON -DLLVM_LINK_LLVM_DYLIB=ON -DMLIR_LINK_MLIR_DYLIB=ON, \
    -DLLVM_BUILD_LLVM_DYLIB=OFF -DLLVM_LINK_LLVM_DYLIB=OFF -DMLIR_LINK_MLIR_DYLIB=OFF, \
"

MLIR_NATIVE_TOOLS_OECMAKE = "\
    -DLLVM_NATIVE_TOOL_DIR=${STAGING_BINDIR_NATIVE} \
    -DMLIR_TABLEGEN=${STAGING_BINDIR_NATIVE}/mlir-tblgen \
    -DMLIR_PDLL_TABLEGEN=${STAGING_BINDIR_NATIVE}/mlir-pdll \
    -DMLIR_IRDL_TO_CPP=${STAGING_BINDIR_NATIVE}/mlir-irdl-to-cpp \
    -DMLIR_LINALG_ODS_YAML_GEN=${STAGING_BINDIR_NATIVE}/mlir-linalg-ods-yaml-gen \
"
MLIR_NATIVE_TOOLS_OECMAKE:class-native = ""

EXTRA_OECMAKE += "\
    -DCMAKE_BUILD_TYPE=MinSizeRel \
    -DLLVM_DIR=${STAGING_LIBDIR}/cmake/llvm \
    -DLLVM_ENABLE_ASSERTIONS=OFF \
    -DLLVM_ENABLE_PIC=ON \
    -DMLIR_ENABLE_BINDINGS_PYTHON=OFF \
    -DMLIR_INCLUDE_TESTS=OFF \
    -DMLIR_INCLUDE_INTEGRATION_TESTS=OFF \
    -DCROSS_TOOLCHAIN_FLAGS_NATIVE='-DCMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain-native.cmake' \
    -DLLVM_BUILD_UTILS=ON \
    -DLLVM_BUILD_TOOLS=ON \
    -DLLVM_INSTALL_TOOLCHAIN_ONLY=OFF \
    ${MLIR_NATIVE_TOOLS_OECMAKE} \
"

EXTRA_OECMAKE:append:class-target = "\
                  -DCMAKE_CROSSCOMPILING:BOOL=ON \
                   ${MLIR_NATIVE_TOOLS_OECMAKE} \
                 "

EXTRA_OECMAKE:append:class-nativesdk = "\
                  -DCMAKE_CROSSCOMPILING:BOOL=ON \
                   ${MLIR_NATIVE_TOOLS_OECMAKE} \
                 "

OECMAKE_TARGET_COMPILE:class-native = "all mlir-src-sharder"

PACKAGES =+ "${PN}-tools"

FILES:${PN}-tools += "\
    ${bindir}/mlir-* \
    ${bindir}/tblgen-* \
"
FILES:${PN}-dev += "${libdir}/objects-*"

BBCLASSEXTEND = "native nativesdk"
