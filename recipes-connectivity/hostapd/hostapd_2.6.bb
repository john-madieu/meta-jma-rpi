HOMEPAGE = "http://hostap.epitest.fi"
SECTION = "kernel/userland"
LICENSE = "GPLv2 | BSD"
LIC_FILES_CHKSUM = "file://README.md;md5=79cddd95f8b7539aced400f6aab996e9"
DEPENDS = "libnl openssl"
SUMMARY = "User space daemon for extended IEEE 802.11 management"

SRC_URI = " \
    https://github.com/jenssegers/RTL8188-hostapd/archive/v2.0.tar.gz \
    file://init \
    file://hostapd.service \
    file://hostapd.conf \
    file://hostapd.wpa_psk \
"

SRC_URI[md5sum] = "84472f837ff675ad73358fc2ca765ee6"
SRC_URI[sha256sum] = "c3b45f2479c2120361f2a9616351dace4ff25d8d0d1a69f2c5c46da0d57dd464"

inherit update-rc.d systemd
INITSCRIPT_NAME = "hostapd"
INITSCRIPT_PARAMS = "start 3 5 2 . stop 20 0 1 6 ."
SYSTEMD_SERVICE_${PN} = "hostapd.service"
SYSTEMD_AUTO_ENABLE_${PN} = "disable"

S = "${WORKDIR}/RTL8188-hostapd-2.0"

CFLAGS += "-I${STAGING_INCDIR}/libnl3"

do_compile() {
    cd ${S}/${PN}
    make CC="${CC}"
    cd ${S}/wpa_supplicant
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
