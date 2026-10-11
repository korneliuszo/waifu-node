SUMMARY = "Datasets, Transforms and Models specific to Computer Vision"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bd7749a3307486a4d4bfefbc81c8b796"
DEPENDS = " \
	protobuf-native python3-pytorch-native python3-sympy-native \
	python3-numpy python3-pillow python3-pytorch \
	jpeg libpng ffmpeg libnvjpeg ninja-native \
"

inherit cmake pkgconfig python3native setuptools3_legacy cuda

SRC_URI = " \
	gitsm://github.com/pytorch/vision.git;protocol=https;branch=release/0.29 \
	file://0001-Use-pkg-config-to-detect-libpng.patch \
"

#PR = "r0"
SRCREV = "fc73f5a8567734ffa4c988b24d5432f0d3005974"

B = "${S}/build"

#export CMAKE_TOOLCHAIN_FILE = "${WORKDIR}/toolchain.cmake"
export FORCE_CUDA = "1"
export TORCHVISION_INCLUDE = "${STAGING_INCDIR}"
export TORCHVISION_LIBRARY = "${STAGING_LIBDIR}"
export TORCHVISION_USE_PNG = "1"
export TORCHVISION_USE_JPEG = "1"
export TORCH_PATH = "${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}/torch"
export TORCH_CUDA_ARCH_LIST = "9.0+PTX;11.0"
export CUDA_HOME = "${CUDA_PATH}"
export CXX_FOR_CUDA
export CMAKE_ARGS = "${OECMAKE_ARGS} ${EXTRA_OECMAKE}"

#

#CXXFLAGS += " \
#	-I${STAGING_INCDIR}/torch/csrc/api/include \
#"

#do_configure () {
#	setuptools3_legacy_do_configure
#}

#do_compile() {
#	setuptools3_legacy_do_compile
#}

#do_install () {
#	setuptools3_legacy_do_install

#	if [ -f ${D}${PYTHON_SITEPACKAGES_DIR}/torchvision-*.egg-info/SOURCES.txt ]; then
#		sed -i 's:${S}/::' ${D}${PYTHON_SITEPACKAGES_DIR}/torchvision-*.egg-info/SOURCES.txt
#	fi
#}

RDEPENDS:${PN} += " \
	python3-numpy \
	python3-pillow \
	python3-pytorch \
	python3-av \
"

SKIP_FILEDEPS:${PN} = '1'
INSANE_SKIP:${PN} = "buildpaths" 
