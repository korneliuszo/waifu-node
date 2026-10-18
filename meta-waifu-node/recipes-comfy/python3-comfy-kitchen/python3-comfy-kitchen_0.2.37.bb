# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Fast Kernel Library for ComfyUI with multiple compute backends"
HOMEPAGE = "https://github.com/Comfy-Org/comfy-kitchen"
# NOTE: License in pyproject.toml is: Apache-2.0
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
#   third_party/cutlass/LICENSE.txt
#   third_party/cutlass/python/LICENSE.txt
#   third_party/cutlass/python/docs/_static/scripts/furo.js.LICENSE.txt
#   third_party/flash-attention/LICENSE
#   third_party/flash-attention/csrc/composable_kernel/LICENSE
#   third_party/flash-attention/csrc/composable_kernel/docs/license.rst
#   third_party/flash-attention/csrc/cutlass/LICENSE.txt
#   third_party/flash-attention/csrc/cutlass/python/LICENSE.txt
#   third_party/flash-attention/csrc/cutlass/python/docs/_static/scripts/furo.js.LICENSE.txt
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6abeb997f7e001c26cec0989af146ec7 \
                    file://third_party/cutlass/LICENSE.txt;md5=869f19a11f1294eb63d1248e7ff505c8 \
                    file://third_party/cutlass/python/LICENSE.txt;md5=c2c0968bbe32f171f90319f299dd248f \
                    file://third_party/cutlass/python/docs/_static/scripts/furo.js.LICENSE.txt;md5=7bbc9f9fc90fbe3bd69de1b43bc8a6be \
                    file://third_party/flash-attention/LICENSE;md5=0f5fe03b6862f8b140b55243259ceaa8 \
                    file://third_party/flash-attention/csrc/composable_kernel/LICENSE;md5=f80a0ab10a5c92ab19ef3a04fb1b96da \
                    file://third_party/flash-attention/csrc/composable_kernel/docs/license.rst;md5=f108c9f3047895b571fd7c1a8f831c39 \
                    file://third_party/flash-attention/csrc/cutlass/LICENSE.txt;md5=1132d6687f729bb3e7bf5d41649513d7 \
                    file://third_party/flash-attention/csrc/cutlass/python/LICENSE.txt;md5=1132d6687f729bb3e7bf5d41649513d7 \
                    file://third_party/flash-attention/csrc/cutlass/python/docs/_static/scripts/furo.js.LICENSE.txt;md5=7bbc9f9fc90fbe3bd69de1b43bc8a6be"

DEPENDS = "python3-ninja-native"
SRC_URI = "gitsm://github.com/Comfy-Org/comfy-kitchen;protocol=https;nobranch=1;branch=master"

SRC_URI += " file://0001-Nanobind-from-env.patch "

# Modify these as desired
PV = "0.2.37"
SRCREV = "be003b7c23c5b01328657955b8bc5d3f073d868e"

inherit cmake python_setuptools_build_meta cuda

DEPENDS += " cuda-cudart python3"
DEPENDS += " cmake-native python3-cmake-native python3-cython-native"

do_compile:prepend(){
    export CUDA_HOME="${CUDA_PATH}"
    export CMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain.cmake
    export NB_DIR="${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}/nanobind/"
    export COMFY_CUDA_ARCHS=110
}

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    cmake
DEPENDS += " python3-nanobind python3-nanobind-native "
RDEPENDS:${PN} += " python3-nanobind"
INSANE_SKIP:${PN} += " already-stripped buildpaths rpaths "
