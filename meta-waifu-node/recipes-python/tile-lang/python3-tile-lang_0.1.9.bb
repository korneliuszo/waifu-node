# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   3rdparty/composable_kernel/LICENSE
#   3rdparty/composable_kernel/docs/license.rst
#   3rdparty/cutlass/LICENSE.txt
#   3rdparty/cutlass/python/LICENSE.txt
#   3rdparty/cutlass/python/docs/_static/scripts/furo.js.LICENSE.txt
#   3rdparty/tvm/3rdparty/cutlass/LICENSE.txt
#   3rdparty/tvm/3rdparty/cutlass/python/LICENSE.txt
#   3rdparty/tvm/3rdparty/cutlass/python/docs/_static/scripts/furo.js.LICENSE.txt
#   3rdparty/tvm/3rdparty/cutlass_fpA_intB_gemm/cutlass/LICENSE.txt
#   3rdparty/tvm/3rdparty/libflash_attn/LICENSE
#   3rdparty/tvm/3rdparty/libflash_attn/cutlass/LICENSE.txt
#   3rdparty/tvm/3rdparty/libflash_attn/cutlass/python/docs/_static/scripts/furo.js.LICENSE.txt
#   3rdparty/tvm/3rdparty/tvm-ffi/3rdparty/libbacktrace/LICENSE
#   3rdparty/tvm/3rdparty/tvm-ffi/LICENSE
#   3rdparty/tvm/3rdparty/tvm-ffi/licenses/LICENSE.libbacktrace.txt
#   3rdparty/tvm/3rdparty/tvm-ffi/licenses/LICENSE.pytorch.txt
#   3rdparty/tvm/3rdparty/zlib/LICENSE
#   3rdparty/tvm/LICENSE
#   3rdparty/tvm/licenses/LICENSE.blockingconcurrentqueue.txt
#   3rdparty/tvm/licenses/LICENSE.builtin_fp16.txt
#   3rdparty/tvm/licenses/LICENSE.cma.txt
#   3rdparty/tvm/licenses/LICENSE.cutlass.txt
#   3rdparty/tvm/licenses/LICENSE.libflash_attn.txt
#   3rdparty/tvm/licenses/LICENSE.zlib.txt
#   3rdparty/tvm/src/relax/distributed/transform/legalize_redistribute.cc
#   3rdparty/tvm/src/relax/transform/legalize_ops.cc
#   LICENSE
#   src/transform/legalize_negative_index.cc
#   src/transform/legalize_safe_memory_access.cc
#   src/transform/legalize_vectorized_loop.cc
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
                    file://LICENSE;md5=e992a0ccae1d29fd6fbb009c0dc9c086 \
"

SRC_URI = "gitsm://github.com/tile-ai/tilelang;protocol=https;nobranch=1;branch=master"

SRC_URI += "file://tile.patch"
SRC_URI += "file://tvm.patch;patchdir=3rdparty/tvm"

# Modify these as desired
PV = "0.1.9+git"
SRCREV = "441c3b06acb23b09d68639532d3a21f427370ced"

inherit python_pep517 cuda cmake

DEPENDS += "python3-scikit-build-core-native python3-patchelf-native python3-cython-native cmake-native python3-z3-solver python3-cmake-native python3-ninja-native cuda-nvrtc cuda-cudart cuda-nvtx cmake-native ninja-native libbacktrace bash-native"

EXTRA_OECMAKE:append = " \
    -DTVM_FFI_USE_LIBBACKTRACE=OFF \
    -DZ3_PATH=${STAGING_DIR_HOST}/usr/lib/python3.14/site-packages/z3/ \    
    -DTILELANG_USE_CUDA_STUBS=OFF \
    -DUSE_CUDA=${STAGING_DIR_HOST}/usr/local/cuda-${CUDA_VERSION} \
    -DCMAKE_CUDA_ARCHITECTURES=110 \
    -DUSE_PYPI_Z3=ON \
"

do_configure:prepend() {
    rm ${S}/.gitmodules || true
}

FILES:${PN} += "/usr/tilelang/lib/*"

RDEPENDS:${PN} += "python3-z3-solver python3-apache-tvm-ffi"

#pathy ciekna
INSANE_SKIP:${PN} += "libdir buildpaths"
INSANE_SKIP:${PN}-dbg += "libdir"

