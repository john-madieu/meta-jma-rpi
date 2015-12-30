FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi:"

SRC_URI += "file://defconfig"
SRC_URI += "file://0001-activated-spi-and-i2c.patch"

kernel_do_configure_prepend() {

	kernel_configure_variable IKCONFIG m
    kernel_configure_variable IKCONFIG_PROC y
    kernel_configure_variable KALLSYMS_ALL y
    kernel_configure_variable PRINTK_TIME y
    kernel_configure_variable DEBUG_USER y

}
