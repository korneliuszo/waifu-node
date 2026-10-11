# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Mistral-common is a library of common utilities for Mistral AI."
# NOTE: License in pyproject.toml is: Apache-2.0
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENCE;md5=c95463c816c77c2a379efd8220ca3a70"

SRC_URI = "git://github.com/mistralai/mistral-common;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "1.11.6+git"
SRCREV = "7edf6f651b3579135f44686e345d51b7e19a536a"

inherit python_setuptools_build_meta

RDEPENDS:${PN} = "python3-jsonschema python3-numpy python3-requests python3-pillow python3-pydantic python3-pydantic-extra-types python3-pycountry python3-tiktoken python3-typing-extensions python3-opencv"
