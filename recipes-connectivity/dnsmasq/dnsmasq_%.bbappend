FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

do_install_append(){
    cat >> ${D}/etc/dnsmasq.conf <<EOF
interface=wlan0
dhcp-range=192.168.21.240,192.168.21.250,255.255.255.0,12h
EOF
}
