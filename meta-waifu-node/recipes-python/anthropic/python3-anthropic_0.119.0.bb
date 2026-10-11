# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "The official Python library for the anthropic API"
HOMEPAGE = "https://github.com/anthropics/anthropic-sdk-python"
# NOTE: License in pyproject.toml is: MIT
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2453eb85b33e21e22cb4fa811c650d75"

DEPENDS = "python3-hatchling-native"
SRC_URI = "git://github.com/anthropics/anthropic-sdk-python;protocol=https;nobranch=1;branch=master \
           file://0001-Slack-off-hatchling-requirement.patch \
           "

# Modify these as desired
PV = "0.119.0+git"
SRCREV = "eb178ecf7b0951226cac47986c520e3dbc658edb"

inherit python_hatchling


# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
DEPENDS += "python3-hatch-fancy-pypi-readme-native"

# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
RDEPENDS:${PN} = "python3-anyio"
RDEPENDS:${PN} = "python3-distro"
RDEPENDS:${PN} = "python3-docstring-parser"
RDEPENDS:${PN} = "python3-httpx"
RDEPENDS:${PN} = "python3-jiter"
RDEPENDS:${PN} = "python3-sniffio"
RDEPENDS:${PN} = "python3-typing-extensions"

RDEPENDS:${PN} = "python3-pydantic"
