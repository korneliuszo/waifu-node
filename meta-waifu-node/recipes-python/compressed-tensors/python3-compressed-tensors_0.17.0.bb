# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   utils/copyright.py
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://utils/copyright.py;md5=eeb9e0ccdd5ed33671a09136e9f7bba2"

DEPENDS = "python3-setuptools-scm-native"
SRC_URI = "git://github.com/vllm-project/compressed-tensors;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.17.0"
SRCREV = "f3b707b7d37515fa7d61c7f65d76fa6867c0b3e0"

inherit setuptools3

RDEPENDS:${PN} += "python3-loguru"
