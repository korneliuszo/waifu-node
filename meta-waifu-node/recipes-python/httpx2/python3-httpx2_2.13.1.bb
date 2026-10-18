# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE.md
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=166cfc32dc0986f87a7e950553b52e5e"

SRC_URI[sha256sum] = "e48744a19e3af5ee48313d0ce5fe941d5422fae5705ea922a4aabf94d7800dfa"

SRC_URI += "file://unuv.patch"

inherit pypi python_hatchling

RDEPENDS:${PN} += "python3-truststore python3-httpcore2 python3-anyio python3-idna"

PYPI_PACKAGE = "httpx2"
