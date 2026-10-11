# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=221b7661d6b6c266ea3e1c11f3fe5bc4"

SRC_URI[sha256sum] = "3c252e748151768a7aefd66824a04a870144f71de48a67aed211749a9ca2a548"

inherit pypi python_poetry_core

PYPI_PACKAGE = "prometheus_fastapi_instrumentator"
