FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi:"

SRC_URI += "file://defconfig"
SRC_URI += "file://0001-activated-spi-and-i2c.patch"

kernel_do_configure_prepend() {

	kernel_configure_variable IKCONFIG m
    kernel_configure_variable IKCONFIG_PROC y
    kernel_configure_variable KALLSYMS_ALL y
    kernel_configure_variable PRINTK_TIME y
    kernel_configure_variable DEBUG_USER y

	#sytemd
	kernel_configure_variable CGROUPS y
	kernel_configure_variable INOTIFY_USER y
	kernel_configure_variable SIGNALFD y
	kernel_configure_variable TIMERFD y
	kernel_configure_variable EPOLL y
	kernel_configure_variable NET y
	kernel_configure_variable SYSFS y
	kernel_configure_variable PROC_FS y
	kernel_configure_variable FHANDLE y
	# Optional but strongly recommended
	kernel_configure_variable IPV6 y
	kernel_configure_variable AUTOFS4_FS y
	kernel_configure_variable TMPFS_POSIX_ACL y
	kernel_configure_variable TMPFS_POSIX_XATTR y
	kernel_configure_variable SECCOMP y
	# Required for PrivateNetwork and PrivateDevices in service units
	kernel_configure_variable NET_NS y
	kernel_configure_variable DEVPTS_MULTIPLE_INSTANCES y

}
