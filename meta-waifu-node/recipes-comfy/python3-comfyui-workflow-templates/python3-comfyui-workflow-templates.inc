# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "ComfyUI workflow templates package"
# NOTE: License in pyproject.toml is: MIT
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b5015b07813999fba0be44f7ca1c24b1"

SRC_URI = "git://github.com/Comfy-Org/workflow_templates;nobranch=1;protocol=https;rev=f846c3771548bd14a17ca9b331b391df031629d4"

inherit python_setuptools_build_meta

do_configure:prepend(){
    nativepython3 scripts/sync/sync_bundles.py
}
