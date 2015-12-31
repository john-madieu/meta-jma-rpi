
DESCRIPTION = " \
	Python-uinput is Python interface to Linux uinput kernel module which \
	allows attaching userspace device drivers into kernel. In practice, \
	Python-uinput makes it dead simple to create virtual joysticks, \
	keyboards and mice for generating arbitrary input events \
	programmatically \
"
SRC_URI = "https://github.com/tuomasjjrasanen/python-uinput/archive/master.zip"

SRC_URI[sha256sum] = "c8975ee4f2c2b94d0e9fb0c97904a0172f3b4d59a6f2d5d7e941c4a824fa184d"
SRC_URI[md5sum] = "11fb5afdad41b45ba06baa9f7cf5ba23"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

DEPENDS = "udev python-pyudev"


S = "${WORKDIR}/${PN}-master"

#inherit setuptools3
inherit distutils
