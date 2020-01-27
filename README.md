# How-to-develop-and-backport-for-Android-2.1-in-2020

Apparently Android Studio refuses to work on Android Projects that target old Android version like 2.1, 2.3 , 2.3.3 and so on.
Personally I don't like this philosophy.  
Actually you can still develop software for old devices that can run on old devices.
This is what you need to do:

0) If you're starting a NEW projects that targets old Android version and you want to save time skipping some quick steps (1/2/3/8/9/10) you can download my customized "clean-retro-project" template from this repository. Otherwise follow the full guide
1) Create a new Android Studio Project;
2) Select "add no activity" or "empty activity". If you use them as starting point you won't have to edit the code later. If you select other pre-made templates, you'll have to edit code and it will only require you time;
3) Click "next" and select the minimum possible api version (in 2020 it's "api 14");
4) DOWNLOAD OLD SDKs: Click the "Android SDK Version" icon, find the entry for the old Android version you want to target and download the corresponding SDK (in 2020 the oldest SDK you can download is 2.1);
5) DOWNLOAD EMULATOR: Click on the AVD Manager icon -> Create Virtual Device -> configure settings (usually old android devices don't have a resolution that exceed 480x800) -> click "Next" -> in the "x86 images" you can find emulators up to Android 2.3 (api 10), while in the "other images" section you can find emulators up to Android 2.0 (api 5)-> download and install the emulator you need;
6) If you absolutely need an x86 emulator image from A.S. that is older than api 10, ask on the internet if someone can share the older emulator (hurry in case!);
7) DOWNLOAD ALTERNATIVE EMULATOR: unfortunately Genymotion and other software houses that develop emualtors just allow to download new emualtors nowday. Anyway you can try to download some old emulator for the web. If youre developing for android 2.3.4 and up I advice you to use the Youwave emulator ([link][youwave]), it works great on Windows!
8) SET MIN SDK: Open the build.gradle file (the "app module" one) and change MinSdkVersion from "14" to the SDK version of the android you're targeting. (You can quickly find the SDK version that corresponds to the old android version you're targeting in the "SDK manager" section;
9) ANDROIDX dependendcies: since we initially created a new A.S. project setting the min api version to 14, A.S. inserted some AndroidX dependencies that can avoid the compilation.  remove the new androidx dependencies from the build.gradle file (the "app module" on). You just need to comment the lines that contain AndroidX elements;
10) HARDWARE ACCELERATION: hw acceleration is not supported on android api <11 (honeycomb).
If can cause runtime problems and it increase RAM usage (memory fills up quickly on old devices!)
-if you want to disable HW acceleration in certain methods only, find those methods that enable HW acceleration and set their parameter to "false" 
-if you want to completely disable HW acceleration, change a line in the manifest file to <application android:hardwareAccelerated="false" ...>
11) THEMES SUPPORT: Themes are not supported on api <14. Pay attention to the "styles.xml" file in res/values. It contanins customizations for you're app theme. You can find your own solution if you want to implements themes, or you can just remove them deleting the styles.xml files and removing the "android:theme="@style/AppTheme" lines from the maifest file;
12) MEMORY: sometimes your backported apps wont't work properly because they take up too much ram.
13)LOGCAT : very probably you'll find error like "can't find method X referenced ni"
12) BACKPORTED LIBRARIES: When searching for Android development resources and tutorials use keywords like "2.2" or "2.3" in your queries, or make an advanced search showing results , you'll find everything;
13) On GitHub you can find libraries that allows to add new features to app that target old Android versions. For example,for one of my projects, I used a library called "[NineOldAndroid][nineold]" that allows to use Android animations on old Android versions;
14) Share the knowledge you acquired working on old android versions!
15) Support me for free spreading my works and attaching my social links to them :)

|  |  |
| ------ | ------ |
| XDA Forum | [link][xda] |
| Tech Blog | [link][cam] |
| Instagram | [link][insta] |
| Youtube | [link][yt] |

[xda]: <http://bit.ly/2NBnhqB>
[insta]: <http://bit.ly/mikel_insta>
[yt]: <http://bit.ly/mikel_YT>
[cam]:<https://cam.tv/mik_el_tech>

[youwave]: <https://youwave.en.uptodown.com/windows/download/41816>
[nineold]:<https://github.com/JakeWharton/NineOldAndroids/>
