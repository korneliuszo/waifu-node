# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Embedded documentation for ComfyUI nodes"
# NOTE: License in pyproject.toml is: GPL-3.0
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI[sha256sum] = "e51bc3b2999bb7a891dbd285f097adb116e0b11b5cdf2376a1bacf90eb2da381"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "comfyui_embedded_docs"
