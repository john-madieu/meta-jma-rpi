SECTION = "libs/multimedia"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.GPL;md5=930e2a5f63425d8dd72dbd7391c43c46"

DEPENDS = " \
	userland \
	python \
	python-distutils \
	python-textutils \
	python-sqlite3 \
	python-pickle \
	python-logging \
	python-elementtree \
	python-curses \
	python-compile \
	python-compiler \
	python-fcntl \
	python-shell \
	python-multiprocessing \
	python-subprocess \
	python-xmlrpc \
	python-netclient \
	python-netserver \
	python-unixadmin \
	python-compression \
	python-json \
	python-unittest \
	python-mmap \
	python-difflib \
	python-pprint \
	python-git \
	python-pkgutil \
	python-pycairo \
	python-mako \
	boost \
	ffmpeg \
	libass \
	mpeg2dec \
	libvorbis \
	curl \
	libgpg-error \
	tiff \
	lzo \
	libtinyxml \
	yajl \
	libxslt \
	taglib \
	libcdio \
	jasper \
	lame \
	xz \
	libcap \
	libpcre \
	libtheora \
	bluez4 \
	fdk-aac \
	libiconv \
	swig-native \
	libsquish \
	jsonschemabuilder-native \
	 \
	libmicrohttpd \
"
# glew

CXXFLAGS += " \
    -I${STAGING_KERNEL_DIR}/include/uapi \
    -I${STAGING_KERNEL_DIR}/include \
    -I${STAGING_DIR}/${MACHINE}${includedir}/interface/vcos/pthreads \
    -I${STAGING_DIR}/${MACHINE}${includedir}/interface/vmcs_host/linux \
"
CFLAGS += " \
    -I${STAGING_KERNEL_DIR}/include/uapi \
    -I${STAGING_KERNEL_DIR}/include \
    -I${STAGING_DIR}/${MACHINE}${includedir}/interface/vcos/pthreads \
    -I${STAGING_DIR}/${MACHINE}${includedir}/interface/vmcs_host/linux \
"

RRECOMMENDS_${PN}_append = "libcurl"

SRC_URI = "git://github.com/xbmc/xbmc.git;protocol=https;branch=Isengard \
           file://0001-Add-4th-argument-for-AC_RUN_IFELSE-to-tinyxml.patch \
"

SRCREV = "cf72616385ea60d9996212ec853032ba23198c5f"

S = "${WORKDIR}/git"

inherit autotools gettext pkgconfig

EXTRA_OECONF="--with-platform=raspberry-pi \
       --enable-player=omxplayer \
       --disable-x11 --disable-xrandr \
       --disable-optical-drive --disable-dvdcss --disable-joystick \
       --disable-crystalhd --disable-vtbdecoder --disable-vaapi \
       --disable-vdpau --disable-projectm --disable-rsxs --disable-fishbmc \
       --enable-alsa --disable-solarwinds --disable-euphoria \
       --disable-plasma --disable-mysql --disable-ssh --disable-samba \
       --enable-udev --enable-optimizations --enable-libusb  \
       LIBS='-lvcos -lvchostif -liconv' \
"

do_configure() {
  export PYTHON_EXTRA_LDFLAGS=""
  export PYTHON_EXTRA_LIBS="-lz"
  export PYTHON_VERSION="${PYTHON_BASEVERSION}"
  export PYTHON_NOVERSIONCHECK="no-check"
  export PYTHON_CPPFLAGS="-I/${STAGING_INCDIR}/python${PYTHON_BASEVERSION}"
  export PYTHON_LDFLAGS="-L${STAGING_LIBDIR} -lpython${PYTHON_BASEVERSION}"

  #We will use the host java during build because there is no native recipe for full openjdk and jamvm is not able to build xbmc
  export JAVA="/usr/bin/java"

  cd ${S}
  ./bootstrap
  echo ${@append_libtool_sysroot(d)}
  echo ${BUILD_SYS}
  echo ${HOST_SYS}
  echo ${TARGET_SYS}
  ${S}/configure  --build=${BUILD_SYS} \
                  --host=${HOST_SYS} \
                  --target=${TARGET_SYS} \
                  ${@append_libtool_sysroot(d)} \
                  ${EXTRA_OECONF}
}

export CFLAGS
export CXXFLAGS

do_compile() {
	cd ${S}
	make ${EXTRA_OEMAKE} -j${BB_NUMBER_THREADS} 
}

do_install() {
	cd ${S}
	oe_runmake DESTDIR=${D} install
}

FILES_${PN} += " \
/usr/local/bin/* /usr/local/include/* \
/usr/local/lib/xbmc \
/usr/local/lib/kodi/addoptions.cmake \
/usr/local/lib/kodi/check_target_platform.cmake \
/usr/local/lib/kodi/addons/library.kodi.guilib/libKODI_guilib-arm.so \
/usr/local/lib/kodi/addons/library.xbmc.pvr/libXBMC_pvr-arm.so \
/usr/local/lib/kodi/addons/library.xbmc.codec/libXBMC_codec-arm.so \
/usr/local/lib/kodi/addons/visualization.glspectrum/opengl_spectrum.vis \
/usr/local/lib/kodi/addons/visualization.waveform/Waveform.vis \
/usr/local/lib/kodi/addons/library.xbmc.addon/libXBMC_addon-arm.so \
/usr/local/lib/kodi/prepare-env.cmake \
/usr/local/lib/kodi/kodi-config.cmake \
/usr/local/lib/kodi/system/players/dvdplayer/libdvdnav-arm.so \
/usr/local/lib/kodi/system/hdhomerun-arm.so \
/usr/local/lib/kodi/system/libcpluff-arm.so \
/usr/local/lib/kodi/system/ImageLib-arm.so \
/usr/local/lib/kodi/system/libexif-arm.so \
/usr/local/lib/kodi/addon-helpers.cmake \
/usr/local/lib/kodi/kodi.bin \
/usr/local/lib/kodi/xbmc-config.cmake \
/usr/local/lib/kodi/handle-depends.cmake \
/usr/local/share/* \
"
FILES_${PN}-dbg += " \
/usr/local/lib/kodi/.debug/* \
/usr/local/lib/kodi/system/.debug/* \
/usr/local/lib/kodi/.debug/* \
/usr/local/lib/kodi/addons/library.kodi.guilib/.debug \
/usr/local/lib/kodi/addons/library.xbmc.pvr/.debug \
/usr/local/lib/kodi/addons/library.xbmc.codec/.debug \
/usr/local/lib/kodi/system/players/dvdplayer/.debug \
/usr/local/lib/kodi/addons/library.xbmc.addon/.debug \
/usr/local/lib/kodi/addons/visualization.glspectrum/.debug \
/usr/local/lib/kodi/addons/visualization.waveform/.debug \
"

INSANE_SKIP_${PN} += "libdir"
INSANE_SKIP_${PN}-dev += "libdir"
INSANE_SKIP_${PN}-dbg += "libdir"
