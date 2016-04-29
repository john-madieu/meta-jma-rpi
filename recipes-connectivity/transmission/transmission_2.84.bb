LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a1923fe8f8ff37c33665716af0ec84f1"

SRC_URI = "http://download.transmissionbt.com/files/transmission-2.84.tar.xz \
	file://settings.json"

SRC_URI[md5sum] = "411aec1c418c14f6765710d89743ae42"
SRC_URI[sha256sum] = "a9fc1936b4ee414acc732ada04e84339d6755cd0d097bcbd11ba2cfc540db9eb"

inherit autotools pkgconfig

DEPENDS = "libevent curl"

EXTRA_OECONF = "--disable-nls"

do_install_append(){
	install -d ${D}/home/root/.config/transmission-daemon/
	install ../settings.json ${D}/home/root/.config/transmission-daemon/
}

FILES_${PN} += "/home/root/.config/transmission-daemon/settings.json"
