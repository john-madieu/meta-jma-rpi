# base on the file: https://github.com/openembedded/openembedded/blob/master/recipes/ffmpeg/ffmpeg_0.6.1.bb
LICENSE = "GPLv2"

SRC_URI = "https://github.com/xbmc/FFmpeg/archive/2.6.4-Isengard.tar.gz"
SRC_URI[md5sum] = "3dbd015fbfea2cbedf1fbd0779ab987e"
SRC_URI[sha256sum] = "2487a6d4ad5701ad22582fc064ce39b60c383eec4958ca1e3218379035fa523f"

LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit autotools pkgconfig

RCONFLICTS_libav = "libav"
RREPLACES_libav = "libav"

PROVIDES = "ffmpeg"

S = "${WORKDIR}/FFmpeg-2.6.4-Isengard"

EXTRA_FFCONF_armv7a = "--cpu=cortex-a8"
EXTRA_FFCONF_mipsel = "--arch=mips"

EXTRA_OECONF = " \
        --arch=${TARGET_ARCH} \
        --cross-prefix=${TARGET_PREFIX} \
        --disable-stripping \
        --enable-cross-compile \
        --enable-pthreads \
	--enable-pic \
	--enable-swscale \
	--enable-nonfree \
	--enable-gpl \
        --extra-cflags="${TARGET_CFLAGS} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS}" \
        --extra-ldflags="${TARGET_LDFLAGS} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS}" \
        --target-os=linux \
	--pkg-config=pkg-config \
        ${EXTRA_FFCONF} \
"

do_configure() {
	cd ${S}
	echo ${D}
	./configure ${EXTRA_OECONF}
}

do_compile() {
	cd ${S}
	oe_runmake -j8
}

do_install() {
	cd ${S}
	oe_runmake DESTDIR=${D} install
	rm -rf ${D}${datadir}/examples
	ls ${D}${datadir}/
}

FULL_OPTIMIZATION_armv7a = "-fexpensive-optimizations  -ftree-vectorize -fomit-frame-pointer -O4 -ffast-math"
BUILD_OPTIMIZATION = "${FULL_OPTIMIZATION}"

FILES_${PN} = "${bindir} ${datadir}/ffprobe.xsd ${datadir}/*.ffpreset"
FILES_${PN}-dev = "${includedir} ${libdir} ${libdir}/pkgconfig/*"
