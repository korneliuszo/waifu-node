SUMMARY = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b114fbe63fdb5f7a91332a4aefb61ee5"

DEPENDS = " \
	coreutils-native git-native shaderc-native \
	python3-cmake-native python3-ninja-native python3-requests-native \
	python3-six-native python3-astunparse-native \
	python3-charset-normalizer-native python3-types-dataclasses-native \
	python3-future-native python3-cffi-native \
	python3-pyyaml-native python3-pybind11-native \
	python3-numpy-native python3-typing-extensions-native \
	sleef glslang gflags zstd \
"

DEPENDS:append:class-target = " \
	zstd-native numactl opencv \
	opencl-headers virtual/libopencl1 \
	shaderc spirv-tools mesa \
	${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'vulkan-headers vulkan-loader', '', d)} \
	python3-numpy python3-typing-extensions python3-pyyaml \
	python3-pybind11 python3-pytorch-native \
        cuda-profiler-api \
        cuda-nvrtc \
        cuda-nvml \
        cuda-nvrtc \
        cuda-nvtx \
        cuda-cupti \
        cudnn \
        libcusparse \
        onnx \
        openblas \
"

inherit cmake python_setuptools_build_meta python3native cuda

# Some gitsm:// has to be git:// due to errors in their upstream
# declaration:
# ERROR: python3-pytorch do_fetch: Fetcher failure: Submodule refers to the parent repository. This will cause deadlock situation in current version of Bitbake.Consider using git fetcher instead.

SRC_URI = " \
	gitsm://github.com/pytorch/pytorch.git;protocol=https;name=pytorch;branch=release/2.14 \
            \
        file://0001-Use-oneDNN-using-its-new-CMake-name.patch \
        file://0005-Use-IsGoogleLoggingInitialized-from-the-Google-publi.patch \
        file://pytorch-fix-vulkan-get_shader-return-type.patch \
	    file://fix-non-void-funcs-with-switch.patch \
	    file://0001-Delete-some-values-from-macros.h.in.patch \
        file://0001-Fix-generate-linker-script-for-cross-compilation.patch \
        file://0002-Fix-CUDA-build-rules-for-cross-compilation.patch \
        file://0003-Use-native-protobuf-compiler.patch \
        file://0004-Prepend-Modules_CUDA_fix-to-CMAKE_MODULE_PATH.patch \
        file://0005-Fix-FMT-installation.patch \
        file://fix-cpp-extension.patch \
        file://fixslop.patch \
        file://shlex.patch \
"
#file://fix-kineto-crosscompile.patch \
#"

#PR = "r1"

SRCREV_FORMAT = "pytorch"
SRCREV_pytorch = "2b3ec34829036a65cd9d1398ea72a0167dc37470"

# These are the git submodule commit IDs
SRCREV_fbjni = "7e1e1fe3858c63c251c637ae41a20de425dde96f"
SRCREV_fp16 = "4dfe081cf6bcd15db339cf2680b9281b8451eeb3"
SRCREV_fxdiv = "b408327ac2a15ec3e43352421954f5b1967701d1"
SRCREV_nnpack = "c07e3a0400713d546e0dea2d5466dd22ea389c73"
SRCREV_nvtx = "3ebbc93ded7285963bff932c678fa367eb393ba6"
SRCREV_vulkanmemalloc = "1d8f600fd424278486eade7ed3e877c99f0846b1"
SRCREV_xnnpack = "51a0103656eff6fc9bfd39a4597923c4b542c883"
SRCREV_aiter = "9a469a608b2c10b7157df573a38d31e5bf4038b4"
SRCREV_benchmark = "299e5928955cc62af9968370293b916f5130916f"
SRCREV_composable_kernel = "f1746955fdaf80a3414de814bf32437686dac347"
SRCREV_cpphttplib = "4d7c9a788de136071ccf0dd4e96239151e2adadb"
SRCREV_cpuinfo = "f858c30bcb16f8effd5ff46996f0514539e17abc"
SRCREV_cudnnfe = "a91f0e04dcea10515f0f776fc5a89535e316a9c8"
SRCREV_cutlass = "da5e086dab31d63815acafdac9a9c5893b1c69e2"
SRCREV_fbgemm = "c246916f9e3804eacc3c95058e51cce02ae00fff"
SRCREV_flash_attention = "fec3a6a18460c1b40f097208d4c16fe8964a679d"
SRCREV_flatbuffers = "a2cd1ea3b6d3fee220106b5fed3f7ce8da9eb757"
SRCREV_fmt = "407c905e45ad75fc29bf0f9bb7c5c2fd3475976f"
SRCREV_gemmlowp = "3fb5c176c17c765a3492cd2f0321b0dab712f350"
SRCREV_gloo = "3135b0b41b67dde590eef0938a0bf3d6238df5f7"
SRCREV_gtest = "52eb8108c5bdec04579160ae17225d66034bd723"
SRCREV_ideep = "e539e0f9774e2018f0d56fe865da66581f692e3d"
SRCREV_ideep_onednn = "03c022d3ffdcee958cfacbe720048e725fdf644c"
SRCREV_ittapi = "0c57540822deb5dae43bef6c1cc9b3be4772a033"
SRCREV_kineto = "b2103f78d13fde4937af010c0ef8e24313568bc5"
SRCREV_kineto_dynolog = "d2ffe0a4e3acace628db49974246b66fc3e85fb1"
SRCREV_kineto_dynolog_dcgm = "ffde4e54bc7249a6039a5e6b45b395141e1217f9"
SRCREV_kineto_dynolog_cpr = "871ed52d350214a034f6ef8a3b8f51c5ce1bd400"
SRCREV_kineto_dynolog_fmt = "cd4af11efc9c622896a3e4cb599fa28668ca3d05"
SRCREV_kineto_dynolog_gflags = "e171aa2d15ed9eb17054558e0b3a6a413bb01067"
SRCREV_kineto_dynolog_gflags_doc = "8411df715cf522606e3b1aca386ddfc0b63d34b4"
SRCREV_kineto_dynolog_glog = "b33e3bad4c46c8a6345525fd822af355e5ef9446"
SRCREV_kineto_dynolog_gtest = "52eb8108c5bdec04579160ae17225d66034bd723"
SRCREV_kineto_dynolog_json = "4f8fba14066156b73f1189a2b8bd568bde5284c5"
SRCREV_kineto_dynolog_pfs = "f68a2fa8ea36c783bdd760371411fcb495aa3150"
SRCREV_kineto_dynolog_pcpp = "b1234816facfdda29845c46696a02998a4af115a"
SRCREV_kineto_fmt = "40626af88bd7df9a5fb80be7b25ac85b122d6c21"
SRCREV_kineto_gtest = "52eb8108c5bdec04579160ae17225d66034bd723"
SRCREV_kleidiai = "d7770c89632329a9914ef1a90289917597639cbe"
SRCREV_mimalloc = "048d969a1c5ee2fb89c226298f41ea38445546ef"
SRCREV_nccl = "b91894bd5b190c874d98a017f93f5daa515b65d0"
SRCREV_mslk = "3d332d1c0c0ac7765852c97b3979c9ef913e037f"
SRCREV_nlohmann = "55f93686c01528224f448c19128836e7df245f72"
SRCREV_onnx = "e709452ef2bbc1d113faf678c24e6d3467696e83"
SRCREV_pocketfft = "0fa0ef591e38c2758e3184c6c23e497b9f732ffa"
SRCREV_protobuf = "d1eca4e4b421cd2997495c4b4e65cea6be4e9b8a"
SRCREV_protobuf_benchmark = "5b7683f49e1e9223cf9927b24f6fd3d6bd82e3f8"
SRCREV_protobuf_gtest = "5ec7f0c4a113e2f18ac2c6cc7df51ad6afc24081"
SRCREV_psimd = "072586a71b55b7f8c584153d223e95687148a900"
SRCREV_pthreadpool = "4fe0e1e183925bf8cfa6aae24237e724a96479b8"
SRCREV_pybind11 = "f5fbe867d2d26e4a0a9177a51f6e568868ad3dc8"
SRCREV_peachpy = "f45429b087dd7d5bc78bb40dc7cf06425c252d67"
SRCREV_sleef = "5a1d179df9cf652951b59010a2d2075372d67f68"
SRCREV_tensorpipe = "2b4cd91092d335a697416b2a3cb398283246849d"

B = "${S}/build"

# -fpermissive is for const vs non-const declarations used from sleef
# -D_LARGEFILE64_SOURCE is needed for the embedded miniz
CXXFLAGS += " \
	-fpermissive \
	-D_LARGEFILE64_SOURCE \
	-I${S}/third_party/ideep/mkl-dnn/include \
	-I${S}/third_party/ideep/include \
	-I${S}/caffe2/mobile/contrib/libopencl-stub/include \
	-Wno-error=maybe-uninitialized \
	-Wno-error=uninitialized \
"

BUILD_CXXFLAGS += " \
	-fpermissive \
	-D_LARGEFILE64_SOURCE \
	-I${S}/third_party/ideep/mkl-dnn/include \
	-I${S}/third_party/ideep/include \
	-I${S}/caffe2/mobile/contrib/libopencl-stub/include \
	-Wno-error=maybe-uninitialized \
	-Wno-error=uninitialized \
"

CFLAGS += " \
	-D_LARGEFILE64_SOURCE \
	-I${S}/third_party/ideep/mkl-dnn/include \
	-I${S}/third_party/ideep/include \
	-I${S}/caffe2/mobile/contrib/libopencl-stub/include \
	-Wno-error=maybe-uninitialized \
	-Wno-error=uninitialized \
"

BUILD_CFLAGS += " \
	-D_LARGEFILE64_SOURCE \
	-I${S}/third_party/ideep/mkl-dnn/include \
	-I${S}/third_party/ideep/include \
	-I${S}/caffe2/mobile/contrib/libopencl-stub/include \
	-Wno-error=maybe-uninitialized \
	-Wno-error=uninitialized \
"

#CUDA_NVCC_EXTRA_FLAGS = "-DCUTE_ARCH_TMA_SM90_ENABLED=1 "

# Leave these variables below un-indented.
# The contents of EXTRA_OECMAKE is split in python code,
# which expects a single space between pieces.

EXTRA_OECMAKE_ARCH_FLAGS ?= "\
-DBLAS=Eigen"

EXTRA_OECMAKE += "\
-DC_HAS_AVX_2_EXITCODE=0 -DC_HAS_AVX_2_EXITCODE__TRYRUN_OUTPUT='' \
-DC_HAS_AVX2_2_EXITCODE=0 -DC_HAS_AVX2_2_EXITCODE__TRYRUN_OUTPUT='' \
-DC_HAS_AVX512_2_EXITCODE=0 -DC_HAS_AVX512_2_EXITCODE__TRYRUN_OUTPUT='' \
-DCXX_HAS_AVX_2_EXITCODE=0 -DCXX_HAS_AVX_2_EXITCODE__TRYRUN_OUTPUT='' \
-DCXX_HAS_AVX2_2_EXITCODE=0 -DCXX_HAS_AVX2_2_EXITCODE__TRYRUN_OUTPUT='' \
-DCXX_HAS_AVX512_2_EXITCODE=0 -DCXX_HAS_AVX512_2_EXITCODE__TRYRUN_OUTPUT='' \
-DSTAGING_LIBDIR=${STAGING_LIBDIR} \
-DBUILD_TEST=OFF \
-DWERROR=OFF \
-DPYTHON_LIBRARY=${STAGING_DIR}/${libdir}/${PYTHON_DIR} \
-DPYTHON_INCLUDE_DIR=${PYTHON_INCLUDE_DIR} \
-DCUDAToolkit_TARGET_DIR=${STAGING_DIR_HOST}/usr/local/cuda-${CUDA_VERSION} \
-DGLIBCXX_USE_CXX11_ABI=1 \
${EXTRA_OECMAKE_ARCH_FLAGS} \
-DUSE_ITT=OFF \
-DUSE_CUDA=ON \
-DUSE_SYSTEM_NVTX=ON \
-DUSE_CUSPARSELT=OFF \
-DCMAKE_CUDA_IMPLICIT_INCLUDE_DIRECTORIES=${STAGING_INCDIR} \
-DUSE_NCCL=OFF \
-DUSE_SYSTEM_NCCL=ON \
-DUSE_FFMPEG=OFF \
-DBUILD_CAFFE2=ON \
-DBUILD_CAFFE2_OPS=ON \
-DCAFFE2_CUSTOM_PROTOC_EXECUTABLE=${STAGING_DIR_NATIVE}${PYTHON_SITEPACKAGES_DIR}/torch/bin/protoc \
-DUSE_SYSTEM_SLEEF=ON \
-DUSE_GOLD_LINKER=ON \
-DUSE_GFLAGS=ON \
-DUSE_GLOG=OFF \
-DUSE_OPENCL=ON \
-DUSE_OPENCV=ON \
-DUSE_MKLDNN=OFF \
-DUSE_VULKAN=${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'ON', 'OFF', d)} \
-DUSE_VULKAN_FP16_INFERENCE=${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'ON', 'OFF', d)} \
-DUSE_VULKAN_RELAXED_PRECISION=${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'ON', 'OFF', d)} \
-DUSE_VULKAN_SHADERC_RUNTIME=${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'ON', 'OFF', d)}"

export TORCH_CUDA_ARCH_LIST = "9.0+PTX;11.0"

EXTRA_OECMAKE:class-native = "\
-DSTAGING_LIBDIR=${STAGING_LIBDIR} \
-DWERROR=OFF \
-DPYTHON_LIBRARY=${STAGING_DIR}/${libdir}/${PYTHON_DIR} \
-DPYTHON_INCLUDE_DIR=${PYTHON_INCLUDE_DIR} \
-DGLIBCXX_USE_CXX11_ABI=1 \
-DBLAS=Eigen \
-DUSE_ITT=OFF \
-DUSE_CUDA=OFF \
-DUSE_FFMPEG=OFF \
-DBUILD_CAFFE2=OFF \
-DBUILD_CAFFE2_OPS=OFF \
-DUSE_SYSTEM_SLEEF=ON \
-DUSE_GFLAGS=ON \
-DUSE_GLOG=OFF \
-DUSE_OPENCL=OFF \
-DUSE_OPENCV=OFF \
-DUSE_VULKAN=OFF \
-DONNX_ML=OFF"

EXTRA_OECMAKE += " -DCMAKE_TOOLCHAIN_FILE='${CMAKE_TOOLCHAIN_FILE}'"
EXTRA_OECMAKE += " -DCMAKE_CUDA_FLAGS='${CUDAFLAGS}'"

export CMAKE_TOOLCHAIN_FILE = "${WORKDIR}/toolchain.cmake"

export CMAKE_ARGS = "${OECMAKE_ARGS} ${EXTRA_OECMAKE}"

do_configure:prepend () {
	#export EXTRA_OECMAKE='${@d.getVar('EXTRA_OECMAKE', expand=True).replace("'","'\\''")}'
	export LD="${HOST_PREFIX}ld"
}

do_compile:prepend () {
	#export EXTRA_OECMAKE='${@d.getVar('EXTRA_OECMAKE', expand=True).replace("'","'\\''")}'
        export LD="${HOST_PREFIX}ld"
}

do_install:prepend () {
	#export EXTRA_OECMAKE='${@d.getVar('EXTRA_OECMAKE', expand=True).replace("'","'\\''")}'
        export LD="${HOST_PREFIX}ld"
}

do_install:append () {
	( cd ${D}${libdir} ; ln -s ${PYTHON_DIR}/site-packages/torch/lib/lib*.so . )

	sed -i 's:set(ATEN_INCLUDE_DIR "${S}/torch/include"):set(ATEN_INCLUDE_DIR "${PYTHON_SITEPACKAGES_DIR}/torch/include"):' ${D}${PYTHON_SITEPACKAGES_DIR}/torch/share/cmake/ATen/ATenConfig.cmake
	sed -i 's:${S}:${TARGET_DBGSRC_DIR}:' ${D}${PYTHON_SITEPACKAGES_DIR}/*.dist-info/licenses/LICENSE
}

PRIVATE_LIBS = " \
	libbackend_with_compiler.so \
	libc10.so \
	libcaffe2_detectron_ops.so \
	libcaffe2_module_test_dynamic.so \
	libcaffe2_observers.so \
	libjitbackend_test.so \
	libshm.so \
	libtorchbind_test.so \
	libtorch_cpu.so \
	libtorch_global_deps.so \
	libtorch_python.so \
	libtorch.so \
"

FILES:${PN}-dev:remove = "${FILES_SOLIBSDEV}"

FILES:${PN} += " \
	${libdir}/*.so \
"

INSANE_SKIP:${PN} = "dev-so"

RDEPENDS:${PN}:append = " python3-filelock "
RDEPENDS:${PN}:append:class-target = " \
	sleef glslang gflags zstd \
	tbb numactl opencv \
	shaderc spirv-tools ${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'vulkan-loader', '', d)} \
	python3-numpy python3-typing-extensions \
	python3-pyyaml python3-pybind11 \
	python3-sympy python3-six python3-onnx \
        python3-statistics python3-terminal \
        gcc \
"

INSANE_SKIP:${PN} = "dev-so already-stripped buildpaths"
INSANE_SKIP:${PN}-dbg = "buildpaths"
SKIP_FILEDEPS:${PN} = '1'

PARALLEL_MAKE = "-j ${@(int)(oe.utils.cpu_count()/3) + 1}"

BBCLASSEXTEND = "native"
