# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "Apache-2.0 & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://bindings/node/LICENSE;md5=195ddbbc20ae4e81cdc1d6b1c0d8846c \
                    file://tokenizers/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://tokenizers/examples/unstable_wasm/www/LICENSE-APACHE;md5=8f7bb094c7232b058c7e9f2e431f389c \
                    file://tokenizers/examples/unstable_wasm/www/LICENSE-MIT;md5=3fd065c2ca72adcd7ddab7b142aee1a1"

SRC_URI = "git://github.com/huggingface/tokenizers;protocol=https;nobranch=1;branch=master"

include python3-tokenizers_deps.inc

# Modify these as desired
PV = "0.22.2rc0"
SRCREV = "95504c029325a5156564d26e4966c189f6b5b426"

PEP517_SOURCE_PATH = "${S}/bindings/python"
CARGO_SRC_DIR = "bindings/python"

RDEPENDS:${PN} += "python3-huggingface-hub"

DEPENDS = "python3-maturin-native"

inherit cargo python_maturin
