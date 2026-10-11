# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fe926eb38efb67f7787efee8bfaabe1d \
                    file://python/LICENSE;md5=fe926eb38efb67f7787efee8bfaabe1d \
                    file://tokenspeed-kernel/python/LICENSE;md5=fe926eb38efb67f7787efee8bfaabe1d \
                    file://tokenspeed-mla/LICENSE;md5=fe926eb38efb67f7787efee8bfaabe1d \
                    file://tokenspeed-scheduler/LICENSE;md5=fe926eb38efb67f7787efee8bfaabe1d"

SRC_URI = "git://github.com/lightseekorg/tokenspeed;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.1.2"
SRCREV = "08baddd13c19d458fd4130f454b82e47817848d5"

inherit python_setuptools_build_meta

PEP517_SOURCE_PATH = "${S}/tokenspeed-mla"

RDEPENDS:${PN} += " \
    python3-apache-tvm-ffi \
    python3-nvidia-cutlass \
    python3-triton \
    python3-pytorch \
"

