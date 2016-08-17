SUMMARY = "A very basic Wayland image with a terminal"
LICENSE = "MIT"

inherit core-image distro_features_check

REQUIRED_DISTRO_FEATURES = "wayland"
DISTRO_FEATURES_remove := "irda nfc pci ipv6 3g pcmcia ptest"
IMAGE_FEATURES += "package-management ssh-server-openssh hwcodecs"

IMAGE_INSTALL += "libcurl kodi omxplayer transmission"
IMAGE_INSTALL += "tvheadend networkmanager samba oscam"
IMAGE_INSTALL += "hostapd dnsmasq wpa-supplicant"


#REQUIRED_DISTRO_FEATURES = "x11"
#CORE_IMAGE_BASE_INSTALL += "weston weston-init weston-examples"
#CORE_IMAGE_BASE_INSTALL += "gtk+3-demo clutter-1.0-examples"
#CORE_IMAGE_BASE_INSTALL += "gdm gnome-shell gtk+3-demo clutter-1.0-examples"
#IMAGE_FEATURES += "splash package-management x11-base x11-sato ssh-server-dropbear hwcodecs"
#IMAGE_INSTALL += "xbmc"
#IMAGE_INSTALL += "kodi gnome-desktop3"
