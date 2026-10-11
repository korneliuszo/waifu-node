# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "A Python implementation of tree structure."
HOMEPAGE = "https://github.com/caesar0301/treelib"
# NOTE: License in pyproject.toml is: Apache-2.0
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=52c5c1ddc1c4e318a739939504ca4db7"

SRC_URI[sha256sum] = "e1be2c6b66ffbfae85079fc4c76fb4909946d01d915ee29ff6795de53aed5d55"

inherit pypi python_poetry_core


# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
#    python

PYPI_PACKAGE = "treelib"
RDEPENDS:${PN} = "python3-six"
