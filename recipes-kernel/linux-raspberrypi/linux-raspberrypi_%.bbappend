FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

#SRC_URI += "file://fragment.cfg"


#SRC_URI += "file://jma_defconfig"
#SRC_URI += "file://0001-activated-spi-and-i2c.patch"

HOOK_KERNEL = " \
    ${@base_contains('DISTRO_FEATURES', 'systemd', 'enable_systemd', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'ppp', 'enable_ppp', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'iptable', 'enable_iptable', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'kdebug', 'enable_kdebug', '', d)} \
"


enable_systemd(){
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

enable_ppp(){
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
}

enable_iptable(){
# iptable, masquarade and nat support
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
# Conntrack support
    kernel_conf_variable NF_CT_PROTO_DCCP m
    kernel_conf_variable NF_CT_PROTO_GRE m
    kernel_conf_variable NF_CT_PROTO_SCTP m
    kernel_conf_variable NF_CT_PROTO_UDPLITE m
    kernel_conf_variable NF_CT_NETLINK m
    kernel_conf_variable NF_CT_NETLINK_TIMEOUT m
    kernel_conf_variable NF_CT_NETLINK_HELPER m
    kernel_conf_variable NF_CONNTRACK_MARK y
    kernel_conf_variable NF_CONNTRACK_SECMARK m
    kernel_conf_variable NF_CONNTRACK_ZONES m
    kernel_conf_variable NF_CONNTRACK_PROCFS m
    kernel_conf_variable NF_CONNTRACK_EVENTS y
    kernel_conf_variable NF_CONNTRACK_TIMEOUT y
    kernel_conf_variable NF_CONNTRACK_TIMESTAMP y
    kernel_conf_variable NF_CONNTRACK_LABELS m
    kernel_conf_variable NF_CONNTRACK_AMANDA m
    kernel_conf_variable NF_CONNTRACK_FTP m
    kernel_conf_variable NF_CONNTRACK_H323 m
    kernel_conf_variable NF_CONNTRACK_IRC m
    kernel_conf_variable NF_CONNTRACK_BROADCAST m
    kernel_conf_variable NF_CONNTRACK_NETBIOS_NS m
    kernel_conf_variable NF_CONNTRACK_SNMP m
    kernel_conf_variable NF_CONNTRACK_PPTP m
    kernel_conf_variable NF_CONNTRACK_SANE m
    kernel_conf_variable NF_CONNTRACK_SIP m
    kernel_conf_variable NF_CONNTRACK_TFTP m
# Network features
    kernel_configure_variable BRIDGE y
    kernel_configure_variable IP_MULTICAST y
    kernel_configure_variable NET_SCHED y
    kernel_configure_variable CFG80211_DEBUGFS y
}


enable_kdebug(){
    kernel_configure_variable IKCONFIG m
    kernel_configure_variable IKCONFIG_PROC y
    kernel_configure_variable KALLSYMS_ALL y
    kernel_configure_variable PRINTK_TIME y
    kernel_configure_variable DEBUG_USER y
}

kernel_do_configure_prepend() {
    kernel_configure_variable I2C_CHARDEV y
    kernel_configure_variable INPUT_POLLDEV y
    kernel_configure_variable HWMON y
    kernel_configure_variable THERMAL_HWMON y
    ${HOOK_KERNEL}
}
