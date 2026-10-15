# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   3rdparty/libbacktrace/LICENSE
#   LICENSE
#   licenses/LICENSE.libbacktrace.txt
#   licenses/LICENSE.pytorch.txt
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = " \
 file://LICENSE;md5=c16953895cf4b5a5334120ac9a33a789 \
"
SRC_URI = "git://github.com/apache/tvm-ffi;protocol=https;nobranch=1;branch=master"

SRC_URI += "file://use-syslibs.patch"
SRC_URI += "file://cmake-config.patch"

# Modify these as desired
PV = "0.1.9.git"
SRCREV = "3050b0a7bd48e04f853027c5fa1f5ab7bc20b856"

inherit cmake python_pep517

DEPENDS += "bash-native dlpack python3-scikit-build-core-native"
DEPENDS:append:class-target = " libbacktrace"

DEPENDS += " cmake-native python3-cmake-native python3-cython-native"

INSANE_SKIP:${PN} = "already-stripped"

RDEPENDS:${PN} += "python3-pytorch python3-numpy python3-ml-dtypes"
RDEPENDS:${PN}:class-target += " libbacktrace"

BBCLASSEXTEND = "native"

do_install:append:class-target (){
        ln -s /usr/include/dlpack ${D}/usr/lib/python3.14/site-packages/tvm_ffi/include/ 
}

SYSROOT_DIRS += "${bindir}"

PEP517_BUILD_OPTS:append = " -Ccmake.define.CMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain.cmake "

PEP517_BUILD_OPTS:append:class-native = "-Ccmake.define.TVM_FFI_USE_LIBBACKTRACE=0"
