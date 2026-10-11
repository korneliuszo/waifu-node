# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "OpenTelemetry Collector Exporters"
HOMEPAGE = "https://github.com/open-telemetry/opentelemetry-python/tree/main/exporter/opentelemetry-exporter-otlp"
# NOTE: License in pyproject.toml is: Apache-2.0
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "af1cde7c33ea8ed624bf04ac49a885730fe44c1f1ad698656e592c38f70ce106"

inherit pypi python_hatchling


# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
#    opentelemetry-exporter-otlp-proto-grpc
#    opentelemetry-exporter-otlp-proto-http

PYPI_PACKAGE = "opentelemetry_exporter_otlp"
