# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "OpenTelemetry Semantic Conventions Extension for Large Language Models"
# NOTE: License in pyproject.toml is: Apache-2.0
# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
# NOTE: Original package / source metadata indicates license is: Apache-2.0
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "153906200d8c1d2f8e09bd78dbef526916023de85ac3dab35912bfafb69ff04c"

inherit pypi python_hatchling


# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
#    opentelemetry-sdk
#    opentelemetry-semantic-conventions

RDEPENDS:${PN} = "python3-opentelemetry-sdk python3-opentelemetry-semantic-conventions"

PYPI_PACKAGE = "opentelemetry_semantic_conventions_ai"
