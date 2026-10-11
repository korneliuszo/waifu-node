# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Model Context Protocol SDK"
HOMEPAGE = "https://modelcontextprotocol.io"
# NOTE: License in pyproject.toml is: MIT
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7ae711d8a91d3871696f50e34ad3c2d7"

DEPENDS = "python3-hatchling-native"
SRC_URI = "git://github.com/modelcontextprotocol/python-sdk;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "1.28.1"
SRCREV = "777b8d06710c140e3606b0d4598e2aa48546c266"

inherit python_hatchling

SRC_URI += "file://disable-dynamic-versioning.patch"
export UV_DYNAMIC_VERSIONING_BYPASS = "1"
#DEPENDS += "python3-uv-dynamic-versioning-native"

RDEPENDS:${PN} += "python3-anyio"
RDEPENDS:${PN} += "python3-httpx"
RDEPENDS:${PN} += "python3-httpx-sse"
RDEPENDS:${PN} += "python3-pydantic-settings"
RDEPENDS:${PN} += "python3-pyjwt"
RDEPENDS:${PN} += "python3-python-multipart"
RDEPENDS:${PN} += "python3-pywin32"
RDEPENDS:${PN} += "python3-sse-starlette"
RDEPENDS:${PN} += "python3-starlette"
RDEPENDS:${PN} += "python3-typing-extensions"
RDEPENDS:${PN} += "python3-typing-inspection"
RDEPENDS:${PN} += "python3-uvicorn"

RDEPENDS:${PN} = "python3-jsonschema python3-pydantic"
