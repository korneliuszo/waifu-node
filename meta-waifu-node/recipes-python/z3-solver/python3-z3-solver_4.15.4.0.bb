# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   core/LICENSE.txt
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://core/LICENSE.txt;md5=5f03ad1486a2e4ce71200ce0f9721557"

SRC_URI[sha256sum] = "928c29b58c4eb62106da51c1914f6a4a55d0441f8f48a81b9da07950434a8946"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "z3_solver"

BBCLASSEXTEND = "native"
