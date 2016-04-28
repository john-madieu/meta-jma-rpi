HOMEPAGE = "http://hostap.epitest.fi"
SECTION = "kernel/userland"
LICENSE = "GPLv2 | BSD"
LIC_FILES_CHKSUM = "file://README;md5=7f393579f8b109fe91f3b9765d26c7d3"
DEPENDS = "libnl openssl"
SUMMARY = "User space daemon for extended IEEE 802.11 management"

SRC_URI = " \
    http://hostap.epitest.fi/releases/hostapd-${PV}.tar.gz \
    file://defconfig \
    file://init \
    file://hostapd.service \
    file://hostapd.conf \
    file://hostapd.wpa_psk \
    file://rtlxdrv.patch  \
"

SRC_URI[md5sum] = "69f9cec3f76d74f402864a43e4f8624f"
SRC_URI[sha256sum] = "8e272d954dc0d7026c264b79b15389ec2b2c555b32970de39f506b9f463ec74a"

inherit update-rc.d systemd
INITSCRIPT_NAME = "hostapd"
INITSCRIPT_PARAMS = "start 3 5 2 . stop 20 0 1 6 ."
SYSTEMD_SERVICE_${PN} = "hostapd.service"
SYSTEMD_AUTO_ENABLE_${PN} = "disable"

S = "${WORKDIR}/${PN}-${PV}"

CFLAGS += "-I${STAGING_INCDIR}/libnl3"

do_configure() {
    install -m 0644 ${WORKDIR}/defconfig ${S}/${PN}/.config
}

do_compile() {
    cd ${WORKDIR}/${PN}-${PV}/${PN}
    # Do not use oe_runmake since it pass '-e' to make command.
    make
}

do_install() {
    install -d ${D}${sbindir} ${D}${sysconfdir}/init.d ${D}${systemd_unitdir}/system/
    install -m 755 ${S}/${PN}/hostapd ${D}${sbindir}
    install -m 755 ${S}/${PN}/hostapd_cli ${D}${sbindir}
    install -m 755 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}/
    install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/hostapd
    install -m 644 ${WORKDIR}/hostapd.service ${D}${systemd_unitdir}/system/
    install -m 644 ${WORKDIR}/hostapd.wpa_psk ${D}${sysconfdir}/
}

CONFFILES_${PN} += "${sysconfdir}/hostapd.conf"


