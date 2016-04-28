SUMMARY = "Video thumbnailer"
DESCRIPTION = "This video thumbnailer can be used to create thumbnails for your video files. The thumbnailer uses ffmpeg to decode frames from the video files, so supported videoformats depend on the configuration flags of ffmpeg."
HOMEPAGE = "http://code.google.com/p/ffmpegthumbnailer/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"
SRC_URI = "http://ffmpegthumbnailer.googlecode.com/files/${PN}-${PV}.tar.gz"

DEPENDS = "ffmpeg jpeg libpng"

SRC_URI[md5sum] = "2b5726894792ef484793dce9568a065a"
SRC_URI[sha256sum] = "a71155339d17201a13fc3ebb649b0d00c7ab2d5a8880da071c8157a69c6f612b"

PR = "r1"

inherit autotools
