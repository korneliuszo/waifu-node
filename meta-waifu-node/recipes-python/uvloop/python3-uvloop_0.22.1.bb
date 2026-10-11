# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Fast implementation of asyncio event loop on top of libuv"
# NOTE: License in pyproject.toml is: MIT License
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   vendor/libuv/LICENSE-docs
#   vendor/libuv/LICENSE-extra
# NOTE: Original package / source metadata indicates license is: Apache & MIT
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "Apache-2.0 & MIT"
LIC_FILES_CHKSUM = "file://LICENSE-APACHE;md5=bb92739ddad0a2811957bd98bdb90474 \
                    file://LICENSE-MIT;md5=489c8bc34154e4b59f5c58e664f7d70f \
                    file://vendor/libuv/LICENSE;md5=74b6f2f7818a4e3a80d03556f71b129b"

SRC_URI = "gitsm://github.com/magicstack/uvloop;protocol=https;nobranch=1;branch=master \
           file://fix-packaging.patch"

# Modify these as desired
PV = "0.22.1"
SRCREV = "74f4c96d3fc5281b1820491d2568de771ea7851b"

inherit autotools python_setuptools_build_meta

DEPENDS += "python3-cython-native"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    Cython~

do_compile:prepend(){
	export LIBUV_CONFIGURE_HOST="${HOST_SYS}"
}
