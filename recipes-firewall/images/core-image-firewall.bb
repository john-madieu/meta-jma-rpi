SUMMARY = "A very basic image with a terminal"
LICENSE = "MIT"

inherit core-image distro_features_check

DISTRO_FEATURES_remove := "wayland x11 opengl irda nfc pci cramfs acpi ext2 \
						 ipv6 3g pcmcia ptest ppp nfs bluetooth alsa phone"
DISTRO_FEATURES_append := " kdebug systemd iptable"

PYTHON = " \
	python-core python-io python-ctypes python-datetime \
	python-importlib python-modules python-json \
	python-subprocess python-re libpython2 python-zlib \
"

IMAGE_FEATURES += "package-management ssh-server-openssh hwcodecs"

IMAGE_INSTALL += "hostapd networkmanager dnsmasq wpa-supplicant"
IMAGE_INSTALL += " linux-firmware "
IMAGE_INSTALL += "iptables iproute2 squid openssl webmin"

#luci
#IMAGE_INSTALL += " lighttpd lighttpd-module-cgi"
#IMAGE_INSTALL += "${PYTHON}"

DISTRO_FEATURES_append = " systemd "
VIRTUAL-RUNTIME_init_manager = "systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
VIRTUAL-RUNTIME_initscripts = ""