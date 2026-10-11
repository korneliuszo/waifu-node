# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "CUTLASS"
HOMEPAGE = "https://github.com/nvidia/cutlass"
# NOTE: License in pyproject.toml is: BSD-3-Clause
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE.txt
#   python/LICENSE.txt
#   python/docs/_static/scripts/furo.js.LICENSE.txt
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=869f19a11f1294eb63d1248e7ff505c8 \
                    file://python/LICENSE.txt;md5=c2c0968bbe32f171f90319f299dd248f \
                    file://python/docs/_static/scripts/furo.js.LICENSE.txt;md5=7bbc9f9fc90fbe3bd69de1b43bc8a6be"

SRC_URI = "gitsm://github.com/NVIDIA/cutlass;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "4.6.1"
SRCREV = "e05f953a5b3d38adc240df2ff928e0421c2abba3"

inherit python_setuptools_build_meta

RDEPENDS:${PN} = "bash python3-networkx python3-numpy python3-pydot python3-scipy python3-treelib"
