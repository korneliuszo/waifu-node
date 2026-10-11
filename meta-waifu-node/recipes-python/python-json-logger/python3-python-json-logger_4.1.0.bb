# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "JSON Log Formatter for the Python Logging Package"
HOMEPAGE = "https://nhairs.github.io/python-json-logger"
# NOTE: License in pyproject.toml is: BSD-2-Clause
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=44a18ebf6d8412ddd5d29217ff70e291"

SRC_URI = "git://github.com/nhairs/python-json-logger;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "4.1.0+git"
SRCREV = "d80c68da770154d9662975b789cabcc9a3e99408"

inherit python_setuptools_build_meta

