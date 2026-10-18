# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "git://github.com/Comfy-Org/ComfyUI;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "0.40.0"
SRCREV = "3541246a9c997f4b8c32f9f88f3c985d02021c79"

RDEPENDS:${PN} += " \
python3-comfyui-frontend-package \
python3-comfyui-workflow-templates \
python3-comfyui-embedded-docs \
python3-pytorch \
python3-torchsde \
python3-torchvision \
python3-numpy \
python3-einops \
python3-transformers \
python3-tokenizers \
python3-sentencepiece \
python3-safetensors \
python3-aiohttp \
python3-yarl \
python3-pyyaml \
python3-pillow \
python3-scipy \
python3-tqdm \
python3-psutil \
python3-alembic \
python3-sqlalchemy \
python3-filelock \
python3-av \
python3-comfy-kitchen \
python3-comfy-aimdo \
python3-requests \
python3-simpleeval \
python3-blake3 \
"

do_configure () {
	:
}

do_compile () {
	:
}

do_install () {
	install -d ${D}/opt/${BPN}/${PV}/
	cp -R ${S}/* ${D}/opt/${BPN}/${PV}/
	rm -rf ${D}/opt/${BPN}/${PV}/.git/
}

FILES:${PN} = "/opt/${BPN}/${PV}/"
