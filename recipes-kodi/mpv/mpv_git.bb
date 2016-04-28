SECTION = "libs/multimedia"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=91f1cb870c1cc2d31351a4d2595441cb"

SRC_URI = "git://github.com/mpv-player/mpv.git"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OECONF="--prefix=${D} --enable-x11 --disable-libass \
--enable-sdl1 --enable-egl-drm --enable-gl \
"

do_configure() {
	./waf configure ${EXTRA_OECONF}
}

do_compile() {
	./waf
}

do_install() {
	./waf install
}
