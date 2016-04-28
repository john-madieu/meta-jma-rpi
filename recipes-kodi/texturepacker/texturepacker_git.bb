SECTION = "libs/multimedia"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://src/TexturePacker.cpp;endline=19;md5=674e5f2ed05ce8b02287d4b8136298de"

SRC_URI = "git://github.com/xbmc/xbmc.git;protocol=https;branch=Isengard"
SRCREV = "cf72616385ea60d9996212ec853032ba23198c5f"

S = "${WORKDIR}/git/tools/depends/native/TexturePacker/"

DEPENDS = " \
	squish \
	m4 \
	libpng \
	"

inherit pkgconfig gettext

BBCLASSEXTEND = "native"
