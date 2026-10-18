# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   3rdparty/cccl/LICENSE
#   3rdparty/cccl/cub/LICENSE.TXT
#   3rdparty/cccl/cudax/LICENSE.TXT
#   3rdparty/cccl/docs/cccl/license.rst
#   3rdparty/cccl/libcudacxx/LICENSE.TXT
#   3rdparty/cccl/libcudacxx/test/LICENSE.TXT
#   3rdparty/cccl/python/cuda_cccl/LICENSE
#   3rdparty/cccl/thrust/LICENSE
#   3rdparty/cutlass/LICENSE.txt
#   3rdparty/cutlass/python/LICENSE.txt
#   3rdparty/cutlass/python/docs/_static/scripts/furo.js.LICENSE.txt
#   3rdparty/nccl/LICENSE.txt
#   3rdparty/nccl/pkg/debian/copyright
#   3rdparty/nixl/.github/workflows/copyright-checks.yml
#   3rdparty/nixl/LICENSE
#   3rdparty/spdlog/LICENSE
#   LICENSE
#   flashinfer/logits_processor/legalization.py
#   licenses/LICENSE.cutlass.txt
#   licenses/LICENSE.flashattention3.txt
#   licenses/LICENSE.spdlog.txt
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = " \
                    file://LICENSE;md5=4136e43dbb01613a45f11fadc8c5416b \
"

SRC_URI = "git://github.com/flashinfer-ai/flashinfer;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.7.2"
SRCREV = "d84335d8c54e0c8f9f7e22faa15c7e34a58abf94"

DEPENDS += "python3-apache-tvm-ffi-native"

inherit python_setuptools_build_meta cuda

RDEPENDS:${PN} += " dlpack dlpack-dev g++ gcc "
INSANE_SKIP:${PN} += " dev-deps "
