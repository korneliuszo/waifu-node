SUMMARY = "The official Python library for the openai API"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0ea6c924de5bc3823bdb7280ae3096c1"
DEPENDS = "python3-hatch-fancy-pypi-readme-native"

PYPI_PACKAGE = "openai"

inherit pypi python_hatchling
SRC_URI[sha256sum] = "e3e6f8bc1ba30ddf381ace1a14340eed381cb984a1a59bd0f34b5be3b5d49cfa"

SRC_URI += "file://unversioned-hatchling.patch"

RDEPENDS:${PN} = " \
	python3-httpx \
	python3-pydantic \
	python3-typing-extensions \
	python3-anyio \
	python3-distro \
	python3-sniffio \
	python3-tqdm \
	python3-jiter \
	python3-aiohttp \
	python3-httpx-aiohttp \
	python3-websockets \
	python3-numpy \
	python3-pandas \
	python3-pandas-stubs \
"
