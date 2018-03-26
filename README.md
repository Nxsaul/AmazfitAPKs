**Source**: [here](https://github.com/RavenLiquid/amazfit-watchface)

# Project prepared for the development APKs watchfaces

Thank's to Malvarez's project.

# Custom Watchfaces for the Amazfit Pace/Stratos

## Disclaimers:
I am not an Android or Java developer, so expect some rookie mistakes (I usually do C#)
If you do this it is at **your own risk!**
I do not provide the original Huami APK/odex files. You will have to source them your self.

## Credit where credit is due
I did not come up with any of this, I just fixed it to work with the latest HuamiWatchFaces code.

Most of the credit goes to Manual Alvarez whose code is [here](https://github.com/manuel-alvarez-alvarez/malvarez-watchface).
And the original thread at XDA Developers [here](https://forum.xda-developers.com/smartwatch/amazfit/watchface-amazfit-watch-t3596912)
The other watchfaces come from Fabio Barbon whose code is [here](https://github.com/drbourbon/drbourbon-watchfaces)

## Help/Discussion/Improvements ##
At this [XDA Developers Thread](https://forum.xda-developers.com/smartwatch/amazfit/project-huamiwatchfaces-based-custom-t3760814) you can get in touch with me if you need more help or have some ways to improve the project.

## Prerequisites
Currently you will need to be able to build this code and have the original Huami code to build against.
- Android Studio
- Android SDK version 21 (Android Studio should prompt you)
- ADB (should come with Android Studio)
- The HuamiWatchFaces.odex or HuamiWatchFaces2.odex from you watch (read below)
- An older HuamiWatchFaces.odex (read below)
- oat2dex ([here](https://github.com/testwhat/SmaliEx))
- dex2jar ([here](https://github.com/pxb1988/dex2jar))
- This repo

## Getting the HuamiWatchFaces files
To get the odex files needed for your watch, connect it via USB.
Then use a terminal/console to:
**For Pace**
adb pull /system/app/HuamiWatchFaces/mips/HuamiWatchFaces.odex
To streamline the instructions further rename the downloaded file to HuamiWatchFaces2.odex

**For Statos**
adb pull /system/app/HuamiWatchFaces/mips/HuamiWatchFaces2.odex

Then you will need an older one, i suggest downloading the APK from [here](http://amazfitcentral.com/2017/08/19/amazfit-amazing-watch-faces/). The link is below the image.

## Converting the files to jars
You will need to convert the odex and dex files in to JAR files so the code can build against it.

java -jar oat2dex.jar -a 22 odex HuamiWatchFaces2.odex
d2j-dex2jar.sh HuamiWatchFaces2.dex

**You should see some GLITCH: zero-width instruction messages**
One of them is HardwareList, this is a problem and we will fix it.
Make sure the result is named HuamiWatchFaces2.jar (rename if not)

Now get the older HuamiWatchFaces file. Open the APK with something like 7Zip and extract the classes.dex and then:
d2j-dex2jar.sh classes.dex

Now you should have 2 jar files, the  HuamiWatchFaces2.jar and probably classes-dex2jar.jar.
Open both of them with something like 7Zip again **don't extract them, just open** and go to com\ingenic\iwds in both.
In your target jar (HuamiWatchFaces2) delete the HardwareList.class, and in your donor (classes-dex2jar) extract the HardwareList.class. 
In your target jar (HuamiWatchFaces2) also delete com\huami\watch\watchface\slpt\Lock\LowPowerClock.class.
Now place the HardwareList.class in your target jar so it looks the same as before but now with the HardwareList.class from your donor.

Great! You should now have a working HuamiWatchFaces2.jar.

## Building the repo ##
If you haven't already, clone this repo to your PC.
Now take your HuamiWatchFaces2.jar and place it in app\src\main\java\es\malvarez\mywatchfaces\libs.

Open the project with Android Studio and build the project with gradle (click the hammer icon in the top left bar).
This should complete with no errors.

You should now have the APK in app\build\outputs\apk.

## Installing the APK ##
adb install app/build/outputs/apk/debug/app-debug.apk 
Or you can take the release one.

After installing you should have these watchfaces

![Malvarez watchface](https://github.com/RavenLiquid/amazfit-watchfaces/raw/master/app/src/main/res/drawable-nodpi/preview_malvarez.png)

![Fuzzy Text](https://github.com/RavenLiquid/amazfit-watchfaces/raw/master/app/src/main/res/drawable-nodpi/preview_fuzzytext.png "")

![Text Time](https://github.com/RavenLiquid/amazfit-watchfaces/raw/master/app/src/main/res/drawable-nodpi/preview_texttime.png?raw=true "")

![Three Lines](https://github.com/RavenLiquid/amazfit-watchfaces/raw/master/app/src/main/res/drawable-nodpi/preview_threelines.png?raw=true "")

![Fancy Digits](https://github.com/RavenLiquid/amazfit-watchfaces/raw/master/app/src/main/res/drawable-nodpi/preview_hugetext.png?raw=true "")

![Big Weather](https://github.com/RavenLiquid/amazfit-watchfaces/raw/master/app/src/main/res/drawable-nodpi/preview_hugeweather.png?raw=true "")
