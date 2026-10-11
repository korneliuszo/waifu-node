# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/Dao-AILab/quack;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.6.1"
SRCREV = "02c7f69881737731173a6a009aeb6f032e449b61"

inherit python_setuptools_build_meta


# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
RDEPENDS:${PN} += "python3-apache-tvm-ffi"
RDEPENDS:${PN} += "python3-einops"
RDEPENDS:${PN} += "python3-nvidia-cutlass"

RDEPENDS:${PN} += "python3-pytorch"
