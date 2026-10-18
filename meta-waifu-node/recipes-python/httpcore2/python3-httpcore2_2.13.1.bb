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
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=77c5a523c15d4a5887b081bba41ca110"

SRC_URI[sha256sum] = "e0aa977abe17e69a3b820a24542a6fa88702676d83880b8d194dcd18408e5103"

SRC_URI += "file://unuv.patch"

inherit pypi python_hatchling

RDEPENDS:${PN} += " python3-truststore python3-h11 "

PYPI_PACKAGE = "httpcore2"
