DESCRIPTION = "Streaming HTTP server for UVC webcam feeds"
HOMEPAGE = "http://mjpg-streamer.sourceforge.net/"

LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

#PV="svn${SRCDATE}"
PR = "r0"

SRCREV = "171"

SRC_URI = " \
    svn://mjpg-streamer.svn.sourceforge.net/svnroot/mjpg-streamer;module=mjpg-streamer;protocol=https \
    file://makefile.patch;apply=yes \
"

S = "${WORKDIR}/mjpg-streamer"
DEPENDS = "jpeg"
RDEPENDS_${PN} = "v4l-utils"

do_install () {
    echo "SOURCEDIR = ${S}"
    install -d ${D}${libdir}/
    install -d ${D}${bindir}/
    install -m 0755 ${S}/mjpg_streamer ${D}${bindir}/
    install -m 0755 ${S}/input_uvc.so ${D}${libdir}/
    install -m 0755 ${S}/input_testpicture.so ${D}${libdir}/
    install -m 0755 ${S}/output_http.so ${D}${libdir}/

    install -d ${D}/usr/share/mjpg_streamer/www
    install -m 0644  ${S}/www/* ${D}/usr/share/mjpg_streamer/www
}

FILES_${PN} = " \
    ${bindir}/mjpg_streamer \
    ${libdir}/input_uvc.so \
    ${libdir}/input_testpicture.so \
    ${libdir}/output_http.so \
    /usr/share/mjpg_streamer/www/* \
    "

