# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   3rdparty/googletest/LICENSE
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/mlc-ai/xgrammar;protocol=https;nobranch=1;branch=master \
           file://python-not-needed.patch \
"

# Modify these as desired
PV = "0.2.8"
SRCREV = "97787376faee5ed8466cfad57c99855e4ce2f6aa"

inherit cmake cuda python3targetconfig

DEPENDS = " \
    dlpack \
    python3-apache-tvm-ffi \
    python3-apache-tvm-ffi-native \
"

RDEPENDS:${PN} = " \
    python3-apache-tvm-ffi \
    python3-pydantic \
    python3-pytorch \
    python3-transformers \
    python3-triton \
    python3-numpy \
    python3-typing-extensions \
"
#    -Dtvm_ffi_DIR:PATH=${STAGING_DIR_HOST}/usr/lib/python3.14/site-packages/tvm_ffi/share/cmake/tvm_ffi 

EXTRA_OECMAKE:append = " \
    -DXGRAMMAR_CUDA_ARCHITECTURES=110 \
    -Dtvm_ffi_ROOT=${STAGING_DIR_HOST}/usr/lib/python3.14/site-packages/tvm_ffi/share/cmake/tvm_ffi/ \
    -Dtvm_ffi_INCLUDE_DIR_OVERRIDE=${STAGING_DIR_HOST}/usr/lib/python3.14/site-packages/tvm_ffi/include/ \
    -Dtvm_ffi_DLPACK_INCLUDE_DIR_OVERRIDE=${STAGING_DIR_HOST}/usr/include/ \
    -Dtvm_ffi_LIB_FILES_OVERRIDE=${STAGING_DIR_HOST}/usr/lib/python3.14/site-packages/tvm_ffi/lib/libtvm_ffi.so \
    "

export TORCH_PATH = "${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}/torch"
export TORCH_CUDA_ARCH_LIST = "11.0"

B = "${S}"

do_install(){
    mkdir -p ${D}${PYTHON_SITEPACKAGES_DIR}
    cp -r ${S}/python/xgrammar ${D}${PYTHON_SITEPACKAGES_DIR}/
}

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}"

