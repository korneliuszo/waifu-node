# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7646f9ee25e49eaf53f89a10665c568c \
                    file://ext/robin_map/LICENSE;md5=7d9128c23e4bdb36bdceedce604442e0"

SRC_URI = "gitsm://github.com/wjakob/nanobind;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "3.1.0"
SRCREV = "68480a9e6883bb1bf65e1925423321e2a89d07f9"

inherit cmake python_setuptools_build_meta

DEPENDS += "python3-ninja-native ninja-native python3-cmake-native cmake-native python3-scikit-build-core-native"


