# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Python toolkit for standardized model hosting container implementations with Amazon SageMaker integration"
# NOTE: License in pyproject.toml is: Apache-2.0
# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
# NOTE: Original package / source metadata indicates license is: Apache-2.0
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI[sha256sum] = "d34589633900e53c3ee5f7c78280a7cf7e4f6532c35e763341a262fc85cbe84a"

inherit pypi python_poetry_core


# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
RDEPENDS:${PN} += "python3-fastapi"
RDEPENDS:${PN} += "python3-httpx"
RDEPENDS:${PN} += "python3-python"
RDEPENDS:${PN} += "python3-starlette"
RDEPENDS:${PN} += "python3-supervisor"

PYPI_PACKAGE = "model_hosting_container_standards"
RDEPENDS:${PN} = "python3-jmespath python3-pydantic \
python3-setuptools"
