
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " file://NetworkManager.conf"
SRC_URI_append = " file://system-connections/"

PACKAGECONFIG = "systemd modemmanager ppp concheck"
DEPENDS += "systemd"

do_install_append(){
	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/NetworkManager/system-connections
	install -m 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}
	install -m 0600 ${WORKDIR}/system-connections/* ${D}${sysconfdir}/NetworkManager/system-connections/
}
