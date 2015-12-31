 
PACKAGECONFIG = "evdev freetype tslib gles2 release dbus udev evdev widgets libs openssl jpeg libpng zlib pulseaudio fontconfig"

#QT_CONFIG_FLAGS_append = " -v -no-reduce-relocations  -no-opengl  -device linux-rasp-pi2-g++  -device-option CROSS_COMPILE=${HOST_PREFIX} "

do_configure_prepend_rpi() {
	
	sed -i 's!load(qt_config)!!' ${S}/mkspecs/linux-oe-g++/qmake.conf
    if ! grep -q '^EGLFS_' ${S}/mkspecs/linux-oe-g++/qmake.conf; then
        cat >> ${S}/mkspecs/linux-oe-g++/qmake.conf <<EOF
QMAKE_INCDIR_EGL = \$\$[QT_SYSROOT]${includedir}/interface/vcos/pthreads \$\$[QT_SYSROOT]${includedir}/interface/vmcs_host/linux
QMAKE_INCDIR_OPENGL_ES2 = \$\${QMAKE_INCDIR_EGL} 
QMAKE_LIBS_EGL = -lEGL -lGLESv2 
EOF

        if [ -d ${S}/src/plugins/platforms/eglfs/deviceintegration/eglfs_brcm ]; then
            cat >> ${S}/mkspecs/linux-oe-g++/qmake.conf <<EOF
EGLFS_DEVICE_INTEGRATION = eglfs_brcm
EOF
        else
            cat >> ${S}/mkspecs/linux-oe-g++/qmake.conf <<EOF
EGLFS_PLATFORM_HOOKS_LIBS = -lbcm_host
EGLFS_PLATFORM_HOOKS_SOURCES = \$\$PWD/../devices/linux-rasp-pi-g++/qeglfshooks_pi.cpp
EOF
        fi
    fi
    cat >> ${S}/mkspecs/linux-oe-g++/qmake.conf <<EOF


load(qt_config)

EOF
}

PACKAGECONFIG_remove = "examples"





