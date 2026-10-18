# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "State-of-the-art Machine Learning for JAX, PyTorch and TensorFlow"
HOMEPAGE = "https://github.com/huggingface/transformers"
# NOTE: License in setup.py/PKGINFO is: Apache 2.0 License
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d15f16de23b5d7f8e28dd073d2ed8b28"

SRC_URI = "git://github.com/huggingface/transformers;protocol=https;nobranch=1;rev=0ea877911a11499053df677befd4b35b172ff502"

inherit setuptools3

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "python3-core"

