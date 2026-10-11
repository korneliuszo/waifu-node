# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "CC0-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ca7f597325e2b5c948ebd2e5c0795cf5"

SRC_URI = "git://github.com/oconnor663/blake3-py;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "1.0.9"
SRCREV = "873bedeb078f4fee1e64a9a5982227eec5af3636"

inherit python_maturin


include ${BPN}_deps.inc

RDEPENDS:${PN} = "python3-typing-extensions"
