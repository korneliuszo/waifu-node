# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "OpenTelemetry Python API"
HOMEPAGE = "https://github.com/open-telemetry/opentelemetry-python/tree/main/opentelemetry-api"
# NOTE: License in pyproject.toml is: Apache-2.0
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "67647e5e9566edcf421166fdf022b3537f818635daa852b289e34604dc6fb33a"

inherit pypi python_hatchling


# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
RDEPENDS:${PN} = "python3-typing-extensions"

PYPI_PACKAGE = "opentelemetry_api"
