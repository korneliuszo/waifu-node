# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "AI Model Dynamic Offloader for ComfyUI"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a12023e7c8c631885794b57de0745d29"

DEPENDS = "python3-setuptools-scm-native"
SRC_URI = "git://github.com/Comfy-Org/comfy-aimdo;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.5.6"
SRCREV = "08b37eec31319b7f245afdc009e34a9861565c25"

inherit python_setuptools_build_meta

