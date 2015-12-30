
DESCRIPTION = " \
	python-networkmanager wraps NetworkManagers D-Bus interface \
	so you can be less verbose when talking to NetworkManager \
	from python. All interfaces have been wrapped in classes, \
	properties are exposed as python properties and function \
	calls are forwarded to the correct interface. \
"
SRC_URI = "http://pypi.python.org/packages/source/p/${PN}/${PN}-${PV}.tar.gz"

SRC_URI[sha256sum] = "a4c7bbae8a1ee3cf370ff0cd2ba2e0f1fb7f8e8f99f8f48a1df624c19a11166f"
SRC_URI[md5sum] = "2cd400a7ca4dcd1ea98c864007032494"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=0fa9ac3fdaf772c22ccc127ff22e0d8e"
DEPENDS = "python-dbus"


S = "${WORKDIR}/${PN}-${PV}"

inherit distutils
