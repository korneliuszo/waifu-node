
## Build
./build.sh bitbake waifu-node-usb-image

## Deploy to /dev/sda
sudo dd of=/dev/sda bs=4M if=build/tmp/deploy/images/jetson-agx-thor-devkit/waifu-node-usb-image-jetson-agx-thor-devkit.rootfs.wic
sudo parted /dev/sda resizepart 2 450G
sudo resize2fs /dev/sda2

## First run prepare
export TORCH_PATH=/usr/lib/python3.14/site-packages/torch/
LD_PRELOAD=/usr/lib/libbacktrace.so.0 tvm-ffi-stubgen /usr/lib/python3.14/site-packages/ --dlls /usr/lib/python3.14/site-packages/xgrammar/libxgrammar_bindings.so --init-lib xgrammar --init-pypkg xgrammar --init-prefix xgrammar.

## Run vllm
export CUDA_HOME=/usr/local/cuda-13.2
export CUDA_PATH=/usr/local/cuda-13.2
export C_INCLUDE_PATH=$C_INCLUDE_PATH:/usr/local/cuda-13.2/include
export CPLUS_INCLUDE_PATH=$CPLUS_INCLUDE_PATH:/usr/local/cuda-13.2/include

export TRITON_PTXAS_PATH="$CUDA_HOME/bin/ptxas"
export TRITON_PTXAS_BLACKWELL_PATH="$TRITON_PTXAS_PATH"
export TORCH_CUDA_ARCH_LIST=11.0
export TORCH_PATH=/usr/lib/python3.14/site-packages/torch/
export CXX=aarch64-oe-linux-g++
export CC=aarch64-oe-linux-gcc
OMP_NUM_THREADS=10 HF_HUB_DISABLE_XET=1 CUDA_HOME=/usr/local/cuda-13.2/ LD_PRELOAD=/usr/lib/libbacktrace.so.0 vllm serve Qwen/Qwen3.6-35B-A3B-FP8 --reasoning-parser qwen3 --enable-auto-tool-choice --tool-call-parser qwen3_coder --gpu-memory-utilization 0.65
