# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://bindings/python/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://safetensors/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/safetensors/safetensors;protocol=https;nobranch=1;branch=master"

include python3-safetensors_deps.inc

# Modify these as desired
PV = "0.8.0"
SRCREV = "a406ca3e7a90598be0cd05a50069cb9bf5ef6ba6"

inherit python_maturin

PEP517_SOURCE_PATH = "${S}/bindings/python"

