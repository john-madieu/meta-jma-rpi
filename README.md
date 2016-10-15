# meta-jma-rpi - Free wifi hotspot

## Description

This layer gives you the possibility to build your own yocto based Hostpot/wifi router.
The layer has been tested on rpi and udoo quad. the main image is `core-image-firewall`


##Changes

###v0.1


 * Initial Version


##Build the image

It is necessary to get all of layers below:

    git clone -b jethro  git://git.yoctoproject.org/poky                       yocto
    git clone -b jethro  git://git.openembedded.org/meta-openembedded          yocto/meta-openembedded
    git clone -b jethro  git://git.yoctoproject.org/meta-raspberrypi           yocto/meta-raspberrypi
    git clone -b master  https://github.com/john-madieu/meta-jma-rpi.git       yocto/meta-jma-rpi

Be sure you use right versions:

    git -C yocto                    reset --hard 347347a
    git -C yocto/meta-openembedded  reset --hard 4fdb203
    git -C yocto/meta-raspberrypi   reset --hard f2cff83
    git -C yocto/meta-jma-rpi       reset --hard v0.1

Initialize build directory with default conf. We use `build` as
directory name, but this name can be changed:

    pushd yocto
    TEMPLATECONF=meta-jma-rpi/conf ./oe-init-build-env ../build
    popd

We need to change $PATH in order to use bitbake tool provided by Yocto. It is
necessary to repeat this command each time you launch a new terminal:

    PATH=$(pwd)/yocto/bitbake/bin:$PATH

Finally, launch compilation from `build`:

    pushd build
    bitbake core-image-firewall
    popd


Results will be located in `build/tmp/deploy/images/raspberrypi2`


##Deploy and Boot on µSD

At this step, you can flash resulting image in your µSD boot device.
From your `build` directory,

    dd if=tmp/deploy/image/raspberrypi2/<image_name>.sdcard of=/dev/<your_media> bs=1M

That is all. Unplug your µSD from your computer to your pi, and then power on.

##TODO

 * Add a captive portal
 * Add iptable NAT rules
 * add squid config
 * add iptable rules (sample) to prevent some app (whats'app snapchat) from network access
