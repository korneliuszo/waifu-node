# ===========================================================================
# waifu-node — Dockerfile for core-image-minimal build
# ===========================================================================
#
# Build:
#   docker build -t waifu-node-build .
#
# Run (single build):
#   docker run --rm \
#     -v $(pwd)/sstate-cache:/workspace/sstate-cache:shared \
#     -v $(pwd)/downloads:/workspace/downloads:shared \
#     -v $(pwd)/build:/workspace/build:shared \
#     -w /workspace \
#     waifu-node-build \
#     bitbake/bin/bitbake core-image-minimal
#
# Shared volumes:
#   sstate-cache/  — shared state cache (persists across builds, shareable)
#   downloads/     — source downloads (persists, reuses fetched sources)
#   build/         — overlay mount (tmp/, work/, rootfs/, deploy/)
#
# How it works:
#   1. bitbake.bin/bitbake finds bblayers.conf in conf/ (via BBPATH/cwd walk)
#   2. TOPDIR = workspace root (/workspace)
#   3. conf/local.conf sets: MACHINE=jetson-agx-thor-devkit, DISTRO=waifu-node
#   4. bitbake.conf in bitbake/conf/ provides base defaults
#   5. build/tmp/ is where actual compilation happens (overlay mount)
# ===========================================================================

FROM ubuntu:26.04

ENV DEBIAN_FRONTEND=noninteractive \
    LANG=C.UTF-8 \
    LC_ALL=C.UTF-8 \
    BB_NUMBER_THREADS=4 \
    PARALLEL_MAKE=-j25

# --- System packages (ubuntu 26.04 + apt list from Kaede) ---
RUN apt-get update && apt-get install -y --no-install-recommends \
        build-essential \
        chrpath \
        cpio \
        debianutils \
        diffstat \
        file \
        gawk \
        gcc \
        git \
        iputils-ping \
        libacl1 \
        lz4 \
        locales \
        python3 \
        python3-git \
        python3-jinja2 \
        python3-pexpect \
        python3-pip \
        python3-subunit \
        socat \
        texinfo \
        unzip \
        wget \
        xz-utils \
        zstd \
        gfortran \
    && locale-gen en_US.UTF-8 \
    && rm -rf /var/lib/apt/lists/*


ARG USER_UID=1000
ARG USER_GID=1000

RUN userdel ubuntu
RUN groupdel users

RUN groupadd -g ${USER_GID} developer && \
    useradd -u ${USER_UID} -g developer -m -s /bin/bash developer

RUN mkdir /workspace
RUN chown ${USER_UID}:${USER_GID} /workspace

WORKDIR /workspace

COPY docker-init docker-init

# --- Volume mount points (ro — workspace is mounted at runtime) ---
RUN mkdir -p /workspace/bitbake \
             /workspace/external-layers \
             /workspace/meta-waifu-node \
             /workspace/conf \
             /workspace/sstate-cache \
             /workspace/downloads \
             /workspace/build

# --- Environment for shared sstate ---
# These guide bitbake to use the shared volumes when mounted.
# When volumes are mounted at runtime, these get overridden by local.conf:
#   DL_DIR=/workspace/downloads
#   SSTATE_DIR=/workspace/sstate-cache
ENV DL_DIR=/workspace/downloads
ENV SSTATE_DIR=/workspace/sstate-cache
ENV TMPDIR=/workspace/build/tmp

# --- Entrypoint ---
# Sources oe-init-build-env so PATH/PYTHONPATH/BUILDDIR are set correctly.
# No implicit bitbake — CMD passes through whatever is given, or falls back
# to running an interactive shell if nothing is specified.
ENTRYPOINT ["/workspace/docker-init"]
CMD ["bash"]
# --- Volumes (all ro — workspace is mounted at runtime) ---
VOLUME [ \
  "/workspace/bitbake", \
  "/workspace/external-layers", \
  "/workspace/meta-waifu-node", \
  "/workspace/conf", \
  "/workspace/sstate-cache", \
  "/workspace/downloads", \
  "/workspace/build" \
]
