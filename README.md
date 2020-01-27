# How-to-develop-and-backport-for-Android-2.1-in-2020

Apparently Android Studio refuses to work on Android Projects that target old Android version like 2.1, 2.3 , 2.3.3 and so on.
Personally I don't like this philosophy.  
Actually you can still develop software for old devices that can run on old devices.
This is what you need to do:

# REQUIRED DOWNLOADS AND ANDROID STUDIO CONFIGURATION
1) DOWNLOAD OLD SDKs: Click the "Android SDK Version" icon, find the entry for the old Android version you want to target and download the corresponding SDK (in 2020 the oldest SDK you can download is 2.1);
2) DOWNLOAD EMULATOR: Click on the AVD Manager icon -> Create Virtual Device -> configure settings (usually old android devices don't have a resolution that exceed 480x800) -> click "Next" -> in the "x86 images" you can find emulators up to Android 2.3 (api 10), while in the "other images" section you can find emulators up to Android 2.0 (api 5)-> download and install the emulator you need;
3) If you absolutely need an x86 emulator image from A.S. that is older than api 10, ask on the internet if someone can share the older emulator (hurry in case!);
4) DOWNLOAD ALTERNATIVE EMULATOR: unfortunately Genymotion and other software houses that develop emualtors just allow to download new emualtors nowdays. Anyway you can try to download some old emulator for the web. If youre developing for android 2.3.4 and up I advice you to use the Youwave emulator ([link][youwave]), it works great on Windows!

# IF YOU WANT TO START A NEW PROJECT USING MY TEMPLATE
5) If you're making a NEW project and you want to skip the steps of the next paragraph, you can download my customized "clean-retro-project" template from this repository. Otherwise continue to the next paragraph;

# IF YOU WANT TO START A NEW PROJECT FROM ANDROID STUDIO
6) Create a new Android Studio Project;
7) Select "add no activity" or "empty activity". If you use them as starting point you won't have to edit the code later. If you select other pre-made templates, you'll have to edit code and it will only require you time;
8) Click "next" and select the minimum possible api version (in 2020 it's "api 14");
9) SET MIN SDK: Open the build.gradle file (the "app module" one) and change MinSdkVersion from "14" to the SDK version of the android you're targeting. (You can quickly find the SDK version that corresponds to the old android version you're targeting in the "SDK manager" section;
10) ANDROIDX dependendcies: since we initially created a new A.S. project setting the min api version to 14, A.S. inserted some AndroidX dependencies that can avoid the compilation.  remove the new androidx dependencies from the build.gradle file (the "app module" on). You just need to comment the lines that contain AndroidX elements;

# IF YOU WANT TO BACKPORT AN EXISTING PROJECT
11) LOGCAT: when backporting you'll constantly have to use logcat to discover what's not working!  
12) DEPRECATED: when backporting you'll often get errors like "can't find method X referenced from class Y", that's because your project is using a method that can't be executed or an object that can't be used in your project, because they require a API level > your project API level. In this case you need to replace those methods with older methods that do (for the most) the same things.
(stupid example: you can't use Floating Buttons on Api 10, but you can use simple Buttons and style them to make them look like Floating Buttons)   
13) SUPPORT LIBRARIES: support libraries are libraries released by google that allows to use newer object and methods on older APIs.
You can add them to your project from the ....................section
The libraries I use for backporting are
---support-v4 library (for api 10 the latest support library is 25.4.0, newer ones require Android 4.0)
---suport-v7 library
14) 3RD PART BACKPORTED LIBRARIES: On GitHub you can find libraries that allows to add new features to app that target old Android versions.
(For example, for one of my projects, I used a library called "[NineOldAndroid][nineold]" that allows to use Android animations on old Android versions);

# OTHER IMPORTANT THINGS YOU NEED TO KNOW
15) THEMES SUPPORT: Themes are not supported on api <14.
Pay attention to the "styles.xml" file in res/values. It contanins customizations for you're app theme.
You can find your own solution if you want to implements themes, or you can just remove them:
---deleting the styles.xml files and
---removing the "android:theme="@style/AppTheme" lines from the maifest file;
16) HARDWARE ACCELERATION: hw acceleration is not supported on android api <11.(honeycomb)
If can cause runtime problems and it increase RAM usage (memory fills up quickly on old devices!)
---if you want to disable HW acceleration in certain methods only, find those methods that enable HW acceleration and set their parameter to "false" 
---if you want to completely disable HW acceleration, change a line in the manifest file to <application android:hardwareAccelerated="false" ...>
17) LOW MEMORY: when your backported apps take up too much ram on old devices they wont't work properly.
To solve this use proguard to optimize your code. Use it to shrink your code and to optimize dex files. You're app will take less space and will be a bit more lightweight;
18) OTHER PERFORMANCE IMPROVEMENTS: Since you lowered the MinSdk version of an existing project probably your project will still contain dependencies that won't be used because you removed or replaced parts of the code.
You can remove the unused dependecies to further reduce ram and cpu usage [link][removedep]
19) DUMMY ACTIVITY: sometimes it's really difficult to backport an app. If you can't even reach the first screen because your app crashes, you can do this:
  ---create a new empty activity
  ---set it as the default activity in your manifest file
  ---if know the app works it means that the problem was in the main activity
  ---if the app still crashes it means that the problem is caused by some code in background
  
# TO MAKE A GOOD PROJECT
20) FIND INFOS: When searching for Android development resources and tutorials use keywords like "2.2" or "2.3" in your queries, or make an advanced search showing results , you'll find everything;
21) Share the knowledge you acquired working on old android versions! (post your projects on the web too)
22) Support me for free spreading my works and attaching my social links to them :)

|  |  |
| ------ | ------ |
| XDA Forum | [link][xda] |
| Tech Blog | [link][cam] |
| Instagram | [link][insta] |
| Youtube | [link][yt] |

# "MIKEL ANDROID" 2.3
It's a project that aims to backport all the new apps and features to Android 2.3
If you want to know more: www.mikelweb.altervista/mikelandroid
[xda]: <http://bit.ly/2NBnhqB>

[insta]: <http://bit.ly/mikel_insta>
[yt]: <http://bit.ly/mikel_YT>
[cam]:<https://cam.tv/mik_el_tech>
[hwaccel]:<https://developer.android.com/guide/topics/graphics/hardware-accel>
[reducedex]:<https://medium.com/vectorly/how-we-reduced-our-app-size-by-72-c2471ba75954>
[removedep]:<https://stackoverflow.com/questions/19379517/how-to-find-remove-unused-dependencies-in-gradle>

[youwave]: <https://youwave.en.uptodown.com/windows/download/41816>
[nineold]:<https://github.com/JakeWharton/NineOldAndroids/>
