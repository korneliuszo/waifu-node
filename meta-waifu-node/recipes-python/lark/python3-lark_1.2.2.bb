# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "a modern parsing library"
HOMEPAGE = "https://github.com/lark-parser/lark"
# NOTE: License in pyproject.toml is: MIT
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ba5edf8d60cf416b5588b1e6f152fcef"

SRC_URI[sha256sum] = "ca807d0162cd16cef15a8feecb862d7319e7a09bdb13aef927968e45040fed80"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "lark"
