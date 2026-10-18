# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Simple and tiny yield-based trampoline implementation."
HOMEPAGE = "https://gitlab.com/ferreum/trampoline"
# NOTE: License in setup.py/PKGINFO is: MIT
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d82bdbd8636ebd36626f8ee73cdcdb99"

SRC_URI = "git://gitlab.com/ferreum/trampoline.git;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.1.2+git"
SRCREV = "1d98f39c3015594e2ac8ed48dccc2f393b4dd82b"

inherit setuptools3

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "python3-core"
