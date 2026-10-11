# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "A new flavour of deep learning operations"
HOMEPAGE = "https://github.com/arogozhnikov/einops"
# NOTE: License in pyproject.toml is: MIT
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77840d30e97b27d9777d8b4040f1f598"

DEPENDS = "python3-hatchling-native"
SRC_URI = "git://github.com/arogozhnikov/einops;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.8.2"
SRCREV = "8e911db71f2e693a0c434b041180388c685ed06f"

inherit python_hatchling

