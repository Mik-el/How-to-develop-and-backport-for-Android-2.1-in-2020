# How-to-develop-and-backport-for-Android-2.1-in-2020
  * If you can extend the retrocompatibility of your apps up to Android 2.1, why shouldn't you do that?!
  * Increase performances of your apps lowering the minimum Android requirements;
  * Decrease size of your apks;
  * Download my free code template: The APK you can generate with my "Clean Retro Project" weights just 24kb!
  
Apparently Android Studio refuses to work on Android Projects that target old Android versions (2.1, 2.3 and so on...)
Personally I don't like this philosophy.  
Actually you can still develop software for old devices that can run on old devices.
This is my quick guide and this is what you need to do:

# Required Downloads and Android Studio configuration
1) DOWNLOAD OLD SDKs: Click the "Android SDK Version" icon, find the entry for the old Android version you want to target and download the corresponding SDK (in 2020 the oldest SDK you can download is 2.1);
2) DOWNLOAD EMULATOR:
   * Click on the AVD Manager icon
   * Create Virtual Device
   * configure settings (usually old android devices don't have a resolution that exceed 480x800)
   * click "Next"
   * in the "x86 images" you can find emulators up to Android 2.3 (api 10), while in the "other images" section you can find emulators up to Android 2.0 (api 5)
   * download and install the emulator you need;
3) If you absolutely need an x86 emulator image from A.S. that is older than api 10, ask on the internet if someone can share the older emulator (hurry before someone unistall it!);
4) DOWNLOAD ALTERNATIVE EMULATOR: unfortunately Genymotion and other software houses that develop emualtors just allow to download new emualtors nowdays. Anyway you can try to download some old emulator for the web. If youre developing for android 2.3.4 and up I advice you to use the Youwave emulator ([link][youwave]), it works great on Windows!

# If you want to start a NEW Project using my template
5) If you're making a NEW project and you want to skip the steps of the next paragraph, you can download my customized "Clean Retro Project" template from this repository. Otherwise continue to the next paragraph;

# If you want to start a NEW Project from Android Studio
6) Create a new Android Studio Project;
7) Select "add no activity" or "empty activity". If you use them as starting point you won't have to edit the code later. If you select other pre-made templates, you'll have to edit code and it will only require you time;
8) Click "next" and select the minimum possible api version (in 2020 it's "api 14");
9) SET MIN SDK: Open the build.gradle file (the "app module" one) and change MinSdkVersion from "14" to the SDK version of the android you're targeting. (You can quickly find the SDK version that corresponds to the old android version you're targeting in the "SDK manager" section;
10) ANDROIDX dependendcies: since we initially created a new A.S. project setting the min api version to 14, A.S. inserted some AndroidX dependencies that can avoid the compilation. Remove the Androidx dependencies from the build.gradle file (the "app module" on) commenting the lines that contain AndroidX elements;

# If you want to backport an existing project
11) LOGCAT: when backporting you'll constantly have to use logcat to discover what's not working!
12) DEPRECATED:
an old Android device with, let's say, Android 2.3 can't run an app developed for Android 4 because the Android 2.3 device do not include in its system files the new APIs introduced with Android 4.  
That's why, when backporting, you'll often get errors like "can't find method X referenced from class Y": your project "tries" to use a method or an object that is not "present" in its system files.
In this case you have 2 option. Let's explore them in the next 2 paragraphs.
13) Try to REPLACE those methods/objects with older methods/objects that do (for the most) the same thing.
(stupid example: you can't use Floating Buttons on Api 10, but you can use simple Buttons and style them to make them look like Floating Buttons).
14) Use SUPPORT LIBRARIES: 
Another option is to inject the new APIs that are not present in the old Android OS directly into your apk. (this will increase the size of you apk a little bit but you'll get a retrocompatible app!)
Here come in our help the Support Libraries, 
You can add them to your project adding the following lines to the "dependencies" section of your app's build.gradle.
    * support-v4 library
    (for api 10 the latest version is 25.4.0, even if the official [page][suppv4] doesn't tell you)
    (ADD this---> implementation 'com.android.support:support-v4:26.0.0') 
    * support-v7 library
    (for api 10 the latest library is 25.4.0 again, and again, the official [page][suppv7] doesn't tell you)
    (ADD this---> implementation 'com.android.support:appcompat-v7:25.4.0')
    * ALL support libraries and their documentation [here][allsupp]   
15) 3RDy PART BACKPORTED LIBRARIES: On GitHub you can find libraries that allows to add new features to app that target old Android versions.
(For example, for one of my projects, I used a library called "[NineOldAndroid][nineold]" that allows to use Android animations on old Android versions);

# Other important things you need to know
16) THEMES SUPPORT: Themes are not supported on api <14.
Pay attention to the "styles.xml" file in res/values. It contanins customizations for you're app theme.
You can find your own solution if you want to implements themes, or you can just remove them:
    * deleting the styles.xml files and
    * removing the "android:theme="@style/AppTheme" lines from the maifest file;
17) HARDWARE ACCELERATION: hw acceleration is not supported on android api <11.(honeycomb)
If can cause runtime problems and it increase RAM usage (memory fills up quickly on old devices!)
    * if you want to disable HW acceleration in certain methods only, find those methods that enable HW acceleration and set their parameter to "false" 
    * if you want to completely disable HW acceleration, change a line in the manifest file to <application android:hardwareAccelerated="false" ...>
18) LOW MEMORY and STORAGE: try to make the fastest an more lightweight app you can do, especially if you want to target old devices too! (if your apps take up too much ram on old devices they wont't work properly!)
To solve this use the Proguard tool to optimize your code and reduce the apk size. Use it to
    * shrink your code ("shrinkResources true") 
    * optimize dex files size ("minifyEnabled true")
    * other useful resources [here][apksize];
19) OTHER PERFORMANCE IMPROVEMENTS: Since you lowered the MinSdk version of an existing project probably your project will still contain dependencies that won't be used because you removed or replaced parts of the code.
Using the Lint tool can remove the unused dependecies to further reduce ram and cpu usage [link][removedep]
20) DUMMY ACTIVITY: sometimes it's really difficult to backport an app. If you can't even reach the first screen because your app crashes, you can do this:
    * create a new empty activity
    * set it as the default activity in your manifest file
    * if the app still crashes it means that the problem is caused by some code in background
    * if know the app works it means that the problem was in the main activity, now you can start to gradually add back features to this activity 
  
# To make a good project
21) FIND INFOS: When searching for Android development resources and tutorials use keywords like "2.2" or "2.3" in your queries, or make an advanced search showing results , you'll find everything;
22) Share the knowledge you acquired working on old android versions! (post your projects on the web too)
23) Support me for free spreading my works and attaching my social links to them :)

|  |  |
| ------ | ------ |
| XDA Forum | [link][xda] |
| Tech Blog | [link][cam] |
| Instagram | [link][insta] |
| Youtube | [link][yt] |

# The "Mik-el Android" Project
It's a project that aims to backport all the new apps and features to Android 2.3.
Soon more news on https://telegram.me/mikelupdates

[xda]: <http://bit.ly/2NBnhqB>
[insta]: <http://bit.ly/mikel_insta>
[yt]: <http://bit.ly/mikel_YT>
[cam]:<https://cam.tv/mik_el_tech>
[hwaccel]:<https://developer.android.com/guide/topics/graphics/hardware-accel>
[reducedex]:<https://medium.com/vectorly/how-we-reduced-our-app-size-by-72-c2471ba75954>
[removedep]:<https://stackoverflow.com/questions/19379517/how-to-find-remove-unused-dependencies-in->

[youwave]: <https://youwave.en.uptodown.com/windows/download/41816>
[nineold]:<https://github.com/JakeWharton/NineOldAndroids/>
[apksize]:<https://www.slideshare.net/PareshMayani/generating-efficient-apkbyreducingsizeandimprovingperformance>
[suppv7]:<https://mvnrepository.com/artifact/com.android.support/appcompat-v7>
[suppv4]:<https://mvnrepository.com/artifact/com.android.support/support-v4>
[allsupp]:<https://developer.android.com/reference/android/support/packages>
