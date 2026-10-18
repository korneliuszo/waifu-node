# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "A high-throughput and memory-efficient inference and serving engine for LLMs"
HOMEPAGE = "https://github.com/vllm-project/vllm"
# NOTE: License in pyproject.toml is: Apache-2.0
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

DEPENDS = "python3-jinja2-native python3-packaging-native \
protobuf-native \
cmake-native ninja-native python3-jinja2-native rust-native cargo-native \
openssl clang-native llvm-mlir-native llvm-native llvm-lit-native python3 libcublas cutlass \
python3-setuptools-scm-native googletest python3-setuptools-rust-native \
python3-pytorch-native python3-pytorch \
python3-sympy python3-mpmath \
python3-sympy-native python3-mpmath-native \
"

SRC_URI = "git://github.com/vllm-project/vllm;protocol=https;branch=releases/v0.32.0;name=default"
SRC_URI += " file://patch-setuptools-ver.patch "
SRC_URI += " file://cuda_enable_hacks.patch "
SRC_URI += " file://cmake-pin.patch "
SRC_URI += " file://disable_empty_dir.patch "
SRC_URI += " file://unpin-torch.patch "

SRC_URI += " git://github.com/triton-lang/triton.git;protocol=https;nobranch=1;rev=0add68262ab0a2e33b84524346cb27cbb2787356;subdir=triton;name=triton "
SRC_URI += " git://github.com/google/googletest;protocol=https;nobranch=1;rev=52eb8108c5bdec04579160ae17225d66034bd723;subdir=googletest;name=googletest "
SRC_URI += " git://github.com/vllm-project/DeepGEMM;protocol=https;nobranch=1;rev=1e1842a833699298f7afc02eefb2ed168fff6938;subdir=deepgemm;name=deepgemm "
SRC_URI += " git://github.com/vllm-project/FlashMLA;protocol=https;nobranch=1;rev=0eee43b12f034b657133cf2afca6a72ebb6efccf;subdir=flashmla;name=flashmla "
SRC_URI += " git://github.com/IST-DASLab/qutlass;protocol=https;nobranch=1;rev=e74319e3405ce6d71965732880f5dc1f52371f64;subdir=qutlass;name=qutlass "
SRC_URI += " git://github.com/vllm-project/flash-attention;protocol=https;nobranch=1;rev=9cd61de38763d712bb6ce56e2a02cc2bf718c89f;subdir=flashattention;name=flashattention "
SRC_URI += " git://github.com/vllm-project/MSA;protocol=https;nobranch=1;rev=968061ab16cb14991a5c7d4c0e8eabf531be0dcf;subdir=fmha_sm100;name=fmha_sm100 "
SRC_URI += " git://github.com/vllm-project/FlashKDA;protocol=https;nobranch=1;rev=17a037d98da546deb4591e967cf961a43c034d8b;subdir=flashkda;name=flashkda "
SRC_URI += " git://github.com/vllm-project/tml-fa4;protocol=https;nobranch=1;rev=75765e76a9c2c012c1f6ecd64577eb646eb4d303;subdir=tml_fa4;name=tml_fa4 "
SRC_URI += " git://github.com/vllm-project/DeepSelect;protocol=https;nobranch=1;rev=d96d33afe1fab0d6066da49cdc91e64c2bee65ea;subdir=deepselect;name=deepselect "

SRC_URI += " file://flashattention-py314.patch;patchdir=${UNPACKDIR}/flashattention "
SRC_URI += " file://removesm100.patch;patchdir=${UNPACKDIR}/flashattention "
SRC_URI += " file://0001-Fix-hopper-Correct-C-syntax-in-epilogue_fwd.hpp-1723.patch;patchdir=${UNPACKDIR}/flashattention "

inherit cmake
inherit cuda

inherit cargo


# Modify these as desired
PV = "0.32.0"
SRCREV_default = "5df63a7203cdb9328d824a27b682e77c7b9928be"

CARGO_SRC_DIR = "rust/"

inherit python_pyo3 python3targetconfig
do_configure:prepend(){
    python_pyo3_do_configure
    cargo_common_do_configure
}

DEPENDS:append = " cutlass cuda-cudart cuda-crt cuda-profiler-api libcublas llvm-mlir-native llvm-mlir"

CMAKE_ARGS = " \
   ${OECMAKE_ARGS} \
  -DPYTHON_LIBRARY=${PYTHON_LIBRARY} \
  -DPYTHON_INCLUDE_DIR=${PYTHON_INCLUDE_DIR} \
  -DCMAKE_INCLUDE_PATH=${STAGING_DIR_HOST}/usr/local/cuda-${CUDA_VERSION}/include \
  -DCUTLASS_DIR=${STAGING_DIR_HOST}/usr/share/cutlass \
  -DCUTLASS_INCLUDE_DIR=${STAGING_DIR_HOST}/usr/local/cuda-${CUDA_VERSION}/include \
  -DLLVM_EXTERNAL_LIT=${STAGING_BINDIR_NATIVE}/lit \
  -DLLVM_NATIVE_TOOL_DIR=${STAGING_BINDIR_NATIVE} \
  -DMLIR_TABLEGEN_EXE=${STAGING_BINDIR_NATIVE}/mlir-tblgen \
  -DMLIR_PDLL_TABLEGEN=${STAGING_BINDIR_NATIVE}/mlir-pdll \
  -DMLIR_IRDL_TO_CPP=${STAGING_BINDIR_NATIVE}/mlir-irdl-to-cpp \
  -DCUDA_TOOLKIT_TARGET_DIR=${STAGING_DIR_HOST}/usr/local/cuda-${CUDA_VERSION} \
  -DCUDA_TOOLKIT_ROOT_DIR=${STAGING_DIR_NATIVE}/usr/local/cuda-${CUDA_VERSION} \
  -DCMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain.cmake \
  -DSTRIP_CUDA_FLAGS=OM \
"

CUDAFLAGS += " --extended-lambda "

export CXX_FOR_CUDA

CUDA_NVCC_ARCH_FLAGS = ""

do_compile:prepend(){
    oe_cargo_fix_env
    export RUST_TARGET_PATH="${RUST_TARGETS_DIR}"
    export OPENSSL_NO_VENDOR=1
    export OPENSSL_DIR="${STAGING_DIR_HOST}/usr"
    nativepython3 ${S}/tools/build_rust.py --release
    setup_target_config
    export PYTHON_LIBRARY=${PYTHON_LIBRARY}
    export PYTHON_INCLUDE_DIR=${PYTHON_INCLUDE_DIR}
    export VLLM_TARGET_DEVICE=cuda
    export VLLM_USE_PRECOMPILED=0
    export VLLM_VERSION_OVERRIDE="${PV}"
    export CUDA_HOME="${CUDA_PATH}"
    export CUDA_TOOLKIT_ROOT="${CUDA_TOOLKIT_ROOT}"
    export TRITON_KERNELS_SRC_DIR="${UNPACKDIR}/triton/"
    export VLLM_CUTLASS_SRC_DIR="${UNPACKDIR}/cutlass/"
    export FMHA_SM100_SRC_DIR="${UNPACKDIR}/fmha_sm100/"
    export DEEPGEMM_SRC_DIR="${UNPACKDIR}/deepgemm/"
    export DEEPSELECT_SRC_DIR="${UNPACKDIR}/deepselect/"
    export GOOGLETEST_DIR="${UNPACKDIR}/googletest/"
    export FLASH_MLA_SRC_DIR="${UNPACKDIR}/flashmla/"  
    export QUTLASS_SRC_DIR="${UNPACKDIR}/qutlass/"
    export VLLM_FLASH_ATTN_SRC_DIR="${UNPACKDIR}/flashattention"
    export CMAKE_ARGS='${CMAKE_ARGS}'
    export PATH="${CUDA_TOOLKIT_ROOT}/bin:${PATH}"
    export TORCH_PATH="${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}/torch"
    export USE_CUDA=1
    export TORCH_CUDA_ARCH_LIST="11.0"
    export Caffe2_DIR="${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}/torch/share/cmake/Caffe2"
    export Torch_DIR="${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}/torch/share/cmake/Torch"
    export TORCH_PATH="${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}/torch"
    export TORCH_INSTALL_PREFIX="${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}/torch"
}

RUST_TARGET_PATH = "${S}/rust/"

include vllm/crates.inc

inherit python_setuptools_build_meta

SRCREV_FORMAT = "default"
#pathy ciekna
INSANE_SKIP:${PN} = "buildpaths" 
INSANE_SKIP:${PN}-dbg = "buildpaths" 
INSANE_SKIP:${PN} += "file-rdeps"

RDEPENDS:${PN} += "python3-uvloop"
RDEPENDS:${PN} += "cuda-nvml"
RDEPENDS:${PN} += "cutlass"

RDEPENDS:${PN} += "python3-pytorch python3-torchvision" 

RDEPENDS:${PN} += "python3-regex"

RDEPENDS:${PN} += "python3-cachetools"
RDEPENDS:${PN} += "python3-psutil"
RDEPENDS:${PN} += "python3-sentencepiece"  
RDEPENDS:${PN} += "python3-numpy"
RDEPENDS:${PN} += "python3-requests"
RDEPENDS:${PN} += "python3-tqdm"
RDEPENDS:${PN} += "python3-blake3"
RDEPENDS:${PN} += "python3-py-cpuinfo"
RDEPENDS:${PN} += "python3-transformers"
RDEPENDS:${PN} += "python3-tokenizers"
RDEPENDS:${PN} += "python3-safetensors"
RDEPENDS:${PN} += "python3-protobuf"
RDEPENDS:${PN} += "python3-fastapi"
RDEPENDS:${PN} += "python3-starlette"
RDEPENDS:${PN} += "python3-aiohttp"
RDEPENDS:${PN} += "python3-openai"
RDEPENDS:${PN} += "python3-pydantic"
RDEPENDS:${PN} += "python3-prometheus-client"
RDEPENDS:${PN} += "python3-pillow"
RDEPENDS:${PN} += "python3-prometheus-fastapi-instrumentator"
RDEPENDS:${PN} += "python3-tiktoken"
RDEPENDS:${PN} += "python3-lm-format-enforcer"
RDEPENDS:${PN} += "python3-llguidance"
RDEPENDS:${PN} += "python3-outlines-core"
RDEPENDS:${PN} += "python3-diskcache"
RDEPENDS:${PN} += "python3-lark"
RDEPENDS:${PN} += "python3-xgrammar"
RDEPENDS:${PN} += "python3-typing-extensions"
RDEPENDS:${PN} += "python3-filelock"
RDEPENDS:${PN} += "python3-partial-json-parser"
RDEPENDS:${PN} += "python3-jsonschema"
RDEPENDS:${PN} += "python3-pyzmq"
RDEPENDS:${PN} += "python3-msgspec"
RDEPENDS:${PN} += "python3-mistral-common"
RDEPENDS:${PN} += "python3-opencv"
RDEPENDS:${PN} += "python3-pyyaml"
RDEPENDS:${PN} += "python3-six"
RDEPENDS:${PN} += "python3-setuptools"
RDEPENDS:${PN} += "python3-einops"
RDEPENDS:${PN} += "python3-compressed-tensors"
RDEPENDS:${PN} += "python3-depyf"
RDEPENDS:${PN} += "python3-cloudpickle"
RDEPENDS:${PN} += "python3-watchfiles"
RDEPENDS:${PN} += "python3-python-json-logger"
RDEPENDS:${PN} += "python3-ninja"
RDEPENDS:${PN} += "python3-pybase64"
RDEPENDS:${PN} += "python3-cbor2"
RDEPENDS:${PN} += "python3-ijson"
RDEPENDS:${PN} += "python3-setproctitle"
RDEPENDS:${PN} += "python3-openai-harmony"
RDEPENDS:${PN} += "python3-anthropic"
RDEPENDS:${PN} += "python3-model-hosting-container-standards"
RDEPENDS:${PN} += "python3-mcp"
RDEPENDS:${PN} += "python3-opentelemetry-sdk"
RDEPENDS:${PN} += "python3-opentelemetry-api"
RDEPENDS:${PN} += "python3-opentelemetry-exporter-otlp"
RDEPENDS:${PN} += "python3-opentelemetry-semantic-conventions-ai"

RDEPENDS:${PN} += "python3-numba"
RDEPENDS:${PN} += "python3-flashinfer-python"
RDEPENDS:${PN} += "python3-apache-tvm-ffi"
RDEPENDS:${PN} += "python3-tile-lang"
#RDEPENDS:${PN} += "python3-nvidia-cudnn-frontend"
#RDEPENDS:${PN} += "python3-nvtx"
RDEPENDS:${PN} += "python3-fastsafetensors"

RDEPENDS:${PN} += "python3-nvidia-cutlass"
RDEPENDS:${PN} += "python3-quack-kernels"

RDEPENDS:${PN} += "python3-tokenspeed-mla"

RDEPENDS:${PN} += "python3-humming-kernels"

