# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "OpenAI's response format for its open-weight model series gpt-oss"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

SRC_URI = "git://github.com/openai/harmony;protocol=https;nobranch=1"

# Modify these as desired
PV = "0.0.8"
SRCREV = "ec7606df9e87e3d0a1fec9f50928c1e407f0c438"

inherit python_maturin python_setuptools3_rust

DEPENDS += "python3-maturin-native"

include python3-openai-harmony_deps.inc

RDEPENDS:${PN} = "python3-pydantic"
