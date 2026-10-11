# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Quantization GEMM Kernel"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2a944942e1496af1886903d274dedb13"

SRC_URI = "git://github.com/inclusionAI/humming;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.1.10"
SRCREV = "4351af3a8fcdce1a8dee50104ba49566af2427fb"

inherit python_setuptools_build_meta cuda

DEPENDS += "python3-setuptools-scm-native"


# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    setuptools_scm

# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
#    safetensors

RDEPENDS:${PN} = "python3-jinja2 python3-numpy python3-pytorch \
python3-tqdm python3-nvidia-ml-py python3-pyelftools \
python3-tabulate python3-triton"
