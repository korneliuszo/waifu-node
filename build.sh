#!/bin/bash
# ===========================================================================
# waifu-node — build.sh
#
# Builds core-image-minimal (or any recipe) using the Dockerfile.
#
# Shared-volume layout:
#   sstate-cache/  — shared state cache (persists across builds)
#   downloads/     — source downloads (persists across builds)
#   build/         — overlay mount (tmp/, work/, rootfs/, deploy/)
#
# Usage:
#   ./build.sh                        # shell
#   ./build.sh bitbake vllm           # vllm recipe
#   ./build.sh --clean                # wipe build/ overlay, keep caches
# ===========================================================================
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

# Docker image name
IMAGE="waifu-node-build:latest"
CONTAINER="waifu-node-build"

# Build flags
CLEAN_BUILD=0
HAS_RECIPE=0
RECIPE=""

# Parse args
while [[ $# -gt 0 ]]; do
    case "$1" in
        --clean)
            CLEAN_BUILD=1
            shift
            ;;
        *)
            RECIPE="$*"
            HAS_RECIPE=1
            break
            ;;
    esac
done

echo ">>> waifu-node build"
echo ">>> Image: ${IMAGE}"
echo ">>> Recipe: ${RECIPE}"
echo ">>> Working directory: ${SCRIPT_DIR}"

# Build the image if it doesn't exist
if ! docker image inspect "${IMAGE}" >/dev/null 2>&1; then
    echo ">>> Building Docker image..."
    docker build --build-arg USER_UID=$(id -u) --build-arg USER_GID=$(id -g) -t "${IMAGE}" .
fi

# Build command — individual ro volumes for source, rw for build dirs
DOCKER_CMD=(
    docker run -it --rm
    --name "${CONTAINER}"
    --user ${UID}:$(id -g)
    -v "${SCRIPT_DIR}/bitbake:/workspace/bitbake:ro"
    -v "${SCRIPT_DIR}/external-layers:/workspace/external-layers:ro"
    -v "${SCRIPT_DIR}/meta-waifu-node:/workspace/meta-waifu-node:ro"
    -v "${SCRIPT_DIR}/conf:/workspace/conf:ro"
    -v "${SCRIPT_DIR}/sstate-cache:/workspace/sstate-cache:rw"
    -v "${SCRIPT_DIR}/downloads:/workspace/downloads:rw"
    -v "${SCRIPT_DIR}/build:/workspace/build:rw"
    -w /workspace
    "${IMAGE}"
)

# Determine CMD: shell by default, bitbake if a recipe is given
if [[ $HAS_RECIPE -eq 1 ]]; then
    echo ">>> Running ${RECIPE}"
    DOCKER_CMD+=(${RECIPE})
else
    echo ">>> Running shell"
fi

if [[ $CLEAN_BUILD -eq 1 ]]; then
    # Wipe build overlay, keep sstate-cache and downloads
    echo ">>> Cleaning build overlay..."
    rm -rf build/tmp build/work build/stamps build/deploy build/sstate-cache
fi

# Run
echo ">>> Running: ${DOCKER_CMD[*]}"
"${DOCKER_CMD[@]}"
