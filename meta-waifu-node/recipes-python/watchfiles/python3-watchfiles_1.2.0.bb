# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Simple, modern and high performance file watching and code reload in python."
HOMEPAGE = "https://github.com/samuelcolvin/watchfiles"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3c69d58fa5f4ff0b4244368d39546751"

SRC_URI = "git://github.com/samuelcolvin/watchfiles;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "1.2.0"
SRCREV = "b6eb04ffaa99c3e656e448c75d137013ffa2ece3"

inherit python_maturin

include python3-watchfiles_deps.inc

# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
RDEPENDS:${PN} = "python3-anyio"
