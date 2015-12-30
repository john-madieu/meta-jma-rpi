# Don't overflow the console with messages, it is very difficult to use
# the system as the systemd-timesyncd prints too much data

do_install_append() {
	sed -i "s/#LogTarget=journal-or-kmsg/LogTarget=journal/" ${D}${sysconfdir}/systemd/system.conf
}

