SECTION = "libs/multimedia"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://NOTICE;md5=087ae5edf3094fbebf2e44334fa2155c"

SRC_URI = "git://github.com/mstorsjo/fdk-aac.git;protocol=https;branch=master"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit autotools gettext pkgconfig

