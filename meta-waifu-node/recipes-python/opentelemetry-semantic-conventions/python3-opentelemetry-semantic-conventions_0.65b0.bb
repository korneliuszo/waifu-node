# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "OpenTelemetry Semantic Conventions"
HOMEPAGE = "https://github.com/open-telemetry/opentelemetry-python/tree/main/opentelemetry-semantic-conventions"
# NOTE: License in pyproject.toml is: Apache-2.0
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "f9b2b81e9d5b64f11bc952075e7e9c7fb0aab075c7fd1c46d597f1b919852d60"

inherit pypi python_hatchling


# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
RDEPENDS:${PN} = " \
python3-opentelemetry-api \
python3-typing-extensions \
"

PYPI_PACKAGE = "opentelemetry_semantic_conventions"
