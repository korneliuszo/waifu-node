# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   COPYRIGHT.txt
#   LICENSE.txt
#   src/pycountry/COPYRIGHT.txt
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYRIGHT.txt;md5=86704a1f40d1cbc71637e42313b0d06d \
                    file://LICENSE.txt;md5=8cf1799061bb2f68625332ab7039e11f \
                    file://src/pycountry/COPYRIGHT.txt;md5=86704a1f40d1cbc71637e42313b0d06d"

SRC_URI = "git://github.com/pycountry/pycountry;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "26.2.16"
SRCREV = "4ba3951667c2ed98363a3640c9022cf1b7a59300"

inherit python_poetry_core

