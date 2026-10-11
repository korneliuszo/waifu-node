# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Structured Text Generation in Rust"
HOMEPAGE = "https://github.com/dottxt-ai/outlines-core"
# NOTE: License in pyproject.toml is: Apache-2.0
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=11f729442d8b8b4859f771bf8f5f014f"

SRC_URI = "git://github.com/dottxt-ai/outlines-core;protocol=https;nobranch=1;branch=master"

include ${BPN}_deps.inc

# Modify these as desired
PV = "0.2.14"
SRCREV = "8466141a4d882572876d39563c9e6be372d918d3"

inherit python_maturin

INSANE_SKIP:${PN} = "already-stripped"
