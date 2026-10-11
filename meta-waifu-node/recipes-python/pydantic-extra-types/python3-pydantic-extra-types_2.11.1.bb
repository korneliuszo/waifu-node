# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Extra Pydantic types."
HOMEPAGE = "https://github.com/pydantic/pydantic-extra-types"
# NOTE: License in pyproject.toml is: MIT
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c84597553618a1fc12741775f5db635d"

DEPENDS = "python3-hatchling-native"
SRC_URI = "git://github.com/pydantic/pydantic-extra-types;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "2.11.1"
SRCREV = "efe22fb050b6020950bb4797d36c4aca3c5340ec"

inherit python_hatchling

RDEPENDS:${PN} = "python3-pydantic python3-typing-extensions"
