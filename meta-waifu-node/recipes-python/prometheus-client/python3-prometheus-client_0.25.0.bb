# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Python client for the Prometheus monitoring system."
HOMEPAGE = "https://github.com/prometheus/client_python"
# NOTE: License in pyproject.toml is: Apache-2.0 AND BSD-2-Clause
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   docs/themes/hugo-geekdoc/LICENSE
#   docs/themes/hugo-geekdoc/static/js/165-06872da1.chunk.min.js.LICENSE.txt
#   docs/themes/hugo-geekdoc/static/js/main-2e274343.bundle.min.js.LICENSE.txt
#   docs/themes/hugo-geekdoc/static/js/mermaid-c274c389.bundle.min.js.LICENSE.txt
#   docs/themes/hugo-geekdoc/static/js/search-16a110ff.bundle.min.js.LICENSE.txt
# NOTE: Original package / source metadata indicates license is: Apache-2.0-AND-BSD-2-Clause
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/prometheus/client_python;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.25.0+git"
SRCREV = "2cd1738fb880fd0e6aa9a02ec48585128388a6cd"

inherit python_setuptools_build_meta

