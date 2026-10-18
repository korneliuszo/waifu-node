# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "81d074d3504f9d190f1694fb526395afbe4608ee43a88adb1262a639e5b4778b"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "torchsde"

RDEPENDS:${PN} += "python3-numpy python3-scipy python3-pytorch python3-trampoline "