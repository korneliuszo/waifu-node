# ===========================================================================
# waifu-node — USB pendrive image for Tegra (Thor/Thor Devkit)
# ===========================================================================
# Builds a wic image with EFI + ext4 rootfs,
# ready to dd onto USB. Uses UEFI boot path instead of CBoot.
#
# Flash to USB:
#   sudo dd if=build/tmp/deploy/images/jetson-agx-thor-devkit/waifu-node-usb-image.wic \
#           of=/dev/sdX bs=4M conv=fsync
# ===========================================================================

DESCRIPTION = "waifu-node USB pendrive image for Tegra Thor (UEFI)"
LICENSE = "MIT"

inherit core-image

# Use EDK2 UEFI bootloader (l4t-launcher) instead of CBoot
PREFERRED_PROVIDER_virtual/bootloader:append = " edk2-firmware-tegra"

# USB mass storage kernel modules
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS:append = " \
    kernel-module-uas \
    kernel-module-usb-storage \
    kernel-module-tegra-xudc \
"

# Stack: openclaw → vllm
PACKAGE_INSTALL:append = " \
    tegra-firmware \
    htop \
    openclaw \
    python3-comfyui \
    cuda-toolkit \
    python3-pytorch \
    l4t-usb-device-mode \
    l4t-launcher-extlinux \
    python3-pip \
    python3-venv \
    vllm \
    ldd \
    rsync \
    python3-dev \
    sshfs-fuse \
    weston \
    firefox \
"

IMAGE_FEATURES:append = " \
    ssh-server-dropbear \
    allow-root-login \
    empty-root-password \
"

INITRAMFS_IMAGE = "waifu-node-initramfs"

IMAGE_ROOTFS_SIZE = "0"

# Image formats: .wic, .wic.bmap, .fitz
IMAGE_FSTYPES:append = " wic wic.bmap"
WKS_FILE = "waifu-node-usb-image.wks.in"

