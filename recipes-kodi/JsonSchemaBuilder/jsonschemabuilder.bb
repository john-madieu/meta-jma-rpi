SECTION = "libs/multimedia"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.GPL;md5=a8119afbffe1b8b7cd2e751480b80ad8"

SRC_URI = "git://github.com/xbmc/xbmc.git;protocol=https;branch=Isengard"
SRCREV = "cf72616385ea60d9996212ec853032ba23198c5f"

S = "${WORKDIR}/git/tools/depends/native/JsonSchemaBuilder/"

inherit autotools pkgconfig gettext

do_compile() {
	cd ${S}
	make
}

do_install() {
	cd ${S}
	install -d ${D}${bindir}
	install bin/JsonSchemaBuilder ${D}${bindir}
}

FILES_${PN} += "JsonSchemaBuilder"

BBCLASSEXTEND = "native"
