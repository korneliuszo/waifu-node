# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Enforce the output format (JSON Schema, Regex etc) of a language model"
HOMEPAGE = "https://github.com/noamgat/lm-format-enforcer"
# NOTE: License in pyproject.toml is: MIT
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a5f68b6c5e23f7828a1f31578d030ccf"

SRC_URI = "git://github.com/noamgat/lm-format-enforcer;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.11.3+git"
SRCREV = "05c7dc3a46305e39c1a67c8622c433d8deda48a2"

inherit python_poetry_core


# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
#    interegular
#    python
#    pyyaml

RDEPENDS:${PN} = "python3-packaging python3-pydantic"
