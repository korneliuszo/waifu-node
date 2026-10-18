SUMMARY = "Pythonic bindings for FFmpeg's libraries."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a9c4cea4308c4521674ecd7c3255d8af"

DEPENDS = "python3-cython-native ffmpeg"

inherit python_setuptools_build_meta pkgconfig

SRC_URI = "git://github.com/PyAV-Org/PyAV.git;protocol=https;branch=master"

SRCREV = "7e3d950a8b72062502c1a60d672f8ca565313af5"

RDEPENDS:${PN} = "ffmpeg"
