SECTION = "libs/multimedia"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ae98663bac55afe5d989919d296f28a"

DEPENDS = "freetype libpng fribidi"

SRC_URI = "git://github.com/libass/libass.git;protocol=https;branch=master"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit autotools gettext pkgconfig

DEPENDS = "libfribidi glib-2.0"

EXTRA_OECONF = "--host=${HOST_SYS}"
