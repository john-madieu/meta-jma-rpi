FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi:"

SRC_URI += "file://defconfig"
SRC_URI += "file://0001-activated-spi-and-i2c.patch"

