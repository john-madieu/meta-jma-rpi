/*
 * base on the file: https://github.com/openembedded/openembedded/blob/master/recipes/ffmpeg/ffmpeg.inc
 */
DESCRIPTION = "FFmpeg is a complete solution to record, convert and stream audio and video"
HOMEPAGE = "http://ffmpeg.mplayerhq.hu/"
AUTHOR = "Fabrice Bellard ffmpeg-devel@mplayerhq.hu"
SECTION = "libs"
LICENSE = "GPLv2+ & LGPLv2.1+"

LICENSE_FLAGS = "commercial"

ARM_INSTRUCTION_SET = "arm"

PE = "2"

DEPENDS = "zlib libogg libvorbis libtheora faad2 ${@base_conditional('ENTERPRISE_DISTRO', '1', '', 'lame liba52', d)}"

INC_PR = "r17"

inherit autotools pkgconfig

LEAD_SONAME = "libavcodec.so"

EXTRA_OECONF = "\
        --enable-pp \
        --enable-shared \
        --enable-pthreads \
        --enable-gpl \
        --cross-prefix=${TARGET_PREFIX} \
        --disable-debug \
        --disable-ffserver \
        --disable-ffplay \
"

EXTRA_OECONF_append_powerpc += "--${@['disable-altivec','enable-altivec'][bb.data.getVar('BASE_PACKAGE_ARCH',d,1) in ['ppce600']]}"

FFMPEG_LIBS = "libavcodec libavdevice libavformat \
               libavutil libpostproc libswscale libavfilter"

SYSROOT_PREPROCESS_FUNCS = " \
  ffmpeg_stage_cleanup \
  ffmpeg_create_compat_links"

ffmpeg_create_compat_links() {
        rm -rf ${SYSROOT_DESTDIR}${includedir}/ffmpeg
        mkdir -m 0755 ${SYSROOT_DESTDIR}${includedir}/ffmpeg
        cd ${SYSROOT_DESTDIR}${includedir}/ffmpeg

        for lib in ${FFMPEG_LIBS}; do
                ln -s ../$lib/*.h '.' || true
        done
}

ffmpeg_stage_cleanup() {
        rm -rf ${SYSROOT_DESTDIR}${libdir}/vhook \
        ${SYSROOT_DESTDIR}${datadir}
}
