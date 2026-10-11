# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Decompile python functions, from bytecode to source code!"
HOMEPAGE = "https://github.com/thuml/depyf"
# NOTE: License in setup.py/PKGINFO is: MIT
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1e86973a9b8454fec01c57df40117936"

SRC_URI = "git://github.com/thuml/depyf;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.20.0"
SRCREV = "b67e16787e8c66324e114b4f2c6024438385b242"

inherit setuptools3

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
# The configs are might not correct, since PACKAGECONFIG does not support expressions as may used in requires.txt - they are just replaced by text.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= "dev"
PACKAGECONFIG[dev] = ",,,python3-autopep8 python3-flake8 python3-pytest"

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS:${PN} += "python3-astor python3-dill"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "python3-core python3-crypt python3-datetime python3-filelock python3-logging python3-pytorch"
