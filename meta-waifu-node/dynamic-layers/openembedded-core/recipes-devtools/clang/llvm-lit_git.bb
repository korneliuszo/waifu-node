#
# SPDX-FileCopyrightText: <text>Copyright 2026 Arm Limited and/or its
# affiliates <open-source-office@...></text>
#
# SPDX-License-Identifier: MIT

SUMMARY = "LIT compiler infrastructure"
DESCRIPTION = "??"
HOMEPAGE = "https://lit.llvm.org"
SECTION = "devel"

require recipes-devtools/clang/common-clang.inc
require recipes-devtools/clang/common-source.inc

LICENSE = "Apache-2.0-with-LLVM-exception"
LIC_FILES_CHKSUM = "file://llvm/utils/lit/LICENSE.TXT;md5=8a15a0759ef07f2682d2ba4b893c9afe"

inherit python_setuptools_build_meta pkgconfig

DEPENDS += "llvm"
DEPENDS:append:class-target = " llvm-lit-native"
DEPENDS:append:class-nativesdk = " llvm-lit-native"

PEP517_SOURCE_PATH = "${S}/llvm/utils/lit"

BBCLASSEXTEND = "native nativesdk"
