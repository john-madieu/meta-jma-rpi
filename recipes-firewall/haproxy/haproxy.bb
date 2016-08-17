SUMMARY = "HAProxy support for NEXT"
HOMEPAGE = "http://www.haproxy.org/"
SECTION = "tools"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://LICENSE;md5=2d862e836f92129cdc0ecccc54eed5e0"

PV = "1.6.3"

SRC_URI = "http://www.haproxy.org/download/1.6/src/haproxy-${PV}.tar.gz"

SRC_URI[md5sum] = "3362d1e268c78155c2474cb73e7f03f9"
SRC_URI[sha256sum] = "fd06b45054cde2c69cb3322dfdd8a4bcfc46eb9d0c4b36d20d3ea19d84e338a7"

do_compile(){
	oe_runmake TARGET="linux2628"
}

do_install(){
	oe_runmake install
}
