# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "A fast serialization and validation library, with builtin support for JSON, MessagePack, YAML, and TOML."
HOMEPAGE = "https://jcristharif.com/msgspec/"
# NOTE: License in pyproject.toml is: BSD-3-Clause
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c21402f8022478021f697044388b97c4"

DEPENDS = "python3-setuptools-scm-native"
SRC_URI = "git://github.com/msgspec/msgspec;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.21.1"
SRCREV = "10c9ac4a8d0a9aacb7854a71f5cf479b47594736"

inherit python_setuptools_build_meta

