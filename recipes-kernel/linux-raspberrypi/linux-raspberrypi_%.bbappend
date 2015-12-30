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
	
	#ppp	
	kernel_configure_variable PPP y
	kernel_configure_variable PPP_BSDCOMP y
	kernel_configure_variable PPP_DEFLATE y
	kernel_configure_variable PPP_ASYNC y
	kernel_configure_variable PPP_SYNC_TTY y
	kernel_configure_variable PPP_MULTILINK y
    kernel_configure_variable PPP_MPPE m
    kernel_configure_variable PPPOE m
    kernel_configure_variable PPP_DEFLATE m
    
    # Masquerade support
    kernel_configure_variable NETFILTER_XTABLES m
    kernel_configure_variable NF_CONNTRACK m
    kernel_configure_variable NF_CONNTRACK_IPV4 m
    kernel_configure_variable NF_NAT_IPV4 m
    kernel_configure_variable IP_NF_TARGET_MASQUERADE m
    kernel_configure_variable IP_NF_IPTABLES m
    kernel_configure_variable IP_NF_FILTER m
    kernel_configure_variable IP_NF_TARGET_REJECT m
    kernel_configure_variable NETFILTER_XTABLES m
    kernel_configure_variable IP_NF_ARPTABLES m
    kernel_configure_variable IP_NF_ARPFILTER m
    kernel_configure_variable IP_NF_ARP_MANGLE m
 
}
