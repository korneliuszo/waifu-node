# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Bindings for the Low-level Guidance (llguidance) Rust library for use within Guidance"
# NOTE: License in pyproject.toml is: MIT
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d4a904ca135bb7bc912156fee12726f0 \
                    file://parser/LICENSE;md5=d4a904ca135bb7bc912156fee12726f0 \
                    file://toktrie/LICENSE;md5=d4a904ca135bb7bc912156fee12726f0 \
                    file://toktrie_hf_downloader/LICENSE;md5=d4a904ca135bb7bc912156fee12726f0 \
                    file://toktrie_hf_tokenizers/LICENSE;md5=d4a904ca135bb7bc912156fee12726f0 \
                    file://toktrie_tiktoken/LICENSE;md5=d4a904ca135bb7bc912156fee12726f0"

SRC_URI = "git://github.com/guidance-ai/llguidance;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "1.7.6+git"
SRCREV = "0384f3f6aab6cebe8abf9b74db0079b96f5837ef"

inherit python_maturin

include python3-llguidance_deps.inc

DEPENDS += "python3-maturin-native"
