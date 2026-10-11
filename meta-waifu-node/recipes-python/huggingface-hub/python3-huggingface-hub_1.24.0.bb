# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Client library to download and publish models, datasets and other repos on the huggingface.co hub"
HOMEPAGE = "https://github.com/huggingface/huggingface_hub"
# NOTE: License in setup.py/PKGINFO is: Apache-2.0
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/huggingface/huggingface_hub;protocol=https;nobranch=1;branch=master"

# Modify these as desired
PV = "1.24.0"
SRCREV = "36fd32c84d630f455a23b9a3bc4dc7b76d19cdde"

inherit setuptools3

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "python3-asyncio python3-click python3-compression python3-core python3-crypt python3-datetime python3-filelock python3-fsspec python3-httpx python3-io python3-json python3-logging python3-multiprocessing python3-netclient python3-numpy python3-pickle python3-pillow python3-pytest python3-pytest-mock python3-shell python3-stringold python3-threading python3-pytorch python3-tqdm python3-unittest"

