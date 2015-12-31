
DESCRIPTION = " \
	pyudev is a LGPL licensed, pure Python binding for libudev, \
	the device and hardware management and information library for Linux. \
	It supports almost all libudev functionality. \
	You can enumerate devices, query device properties and attributes or monitor devices,  \
	including asynchronous monitoring with threads,  \
	or within the event loops of Qt, Glib or wxPython. \
"
SRC_URI = "git://github.com/pyudev/pyudev.git;branch=master-0.19"
SRCREV = "e35a2ed06ddcdba5414cb037862db513bb5f3af2"
LICENSE = "LGPL"

S = "${WORKDIR}/git"
#inherit setuptools3
inherit distutils
