# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "High-performance safetensors model loader"
# NOTE: License in pyproject.toml is: Apache-2.0
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7ce291c8c9e6d16173fef6c07c312f9c"

DEPENDS = "python3-pybind11-native"
SRC_URI = "git://github.com/foundation-model-stack/fastsafetensors;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.3.3+git"
SRCREV = "daf6cc3fd803ecbf85865062c860122bc42ffefa"

inherit python_setuptools_build_meta

RDEPENDS:${PN} = "python3-typer"
