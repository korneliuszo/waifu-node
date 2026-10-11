# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Iterative JSON parser with standard Python iterator interfaces"
HOMEPAGE = "https://github.com/ICRAR/ijson"
# NOTE: License in pyproject.toml is: BSD-3-Clause AND ISC
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE.txt
# NOTE: Original package / source metadata indicates license is: BSD-3-Clause-AND-ISC
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "BSD-3-Clause & ISC & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=37546ad605c4c34c5c9faf9e1f9c2559 \
                    file://cextern/yajl/COPYING;md5=39af6eb42999852bdd3ea00ad120a36d"

SRC_URI = "gitsm://github.com/ICRAR/ijson;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "3.5.1"
SRCREV = "d991ad9ed9afdd140f1e234cb653cc12a7439904"

inherit python_setuptools_build_meta

