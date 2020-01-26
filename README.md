# How-to-develop-and-backport-for-Android-2.1-in-2020

Apparently Android Studio refuses to work on Android Projects that target old Android version like 2.1, 2.3 , 2.3.3 and so on.
Personally I don't like this philosophy.  
Actually you can still develop software for old devices that can run on old devices.
This is what you need to do:


1) Create a new Android Studio Project;
2) Select "add no activity" or "empty activity". If you use them as starting point you won't have to edit the code later. If you select other pre-made templates, you'll have to edit code and it will only require you time;
3)Click "next" and select the minimum possible api version (in 2020 it's "api 14");
4) DOWNLOAD OLD SDKs: Click the "Android SDK Version" icon, find the entry for the old Android version you want to target and download the corresponding SDK (in 2020 the oldest SDK you can download is 2.1);
5) DOWNLOAD EMULATOR: Click on the AVD Manager icon -> Create Virtual Device -> configure settings (usually old android devices don't have a resolution that exceed 480x800) -> click "Next" -> in the "x86 images" you can find emulators up to Android 2.3 (api 10), while in the "other images" section you can find emulators up to Android 2.0 (api 5)-> download and install the emulator you need;
6) If you absolutely need an x86 emulator image from google that is older than api 10 ask on the internet if someone can share the older emulator;
7) DOWNLOAD ALTERNATIVE EMULATOR: unfortunately Genymotion and other software houses that develop emualtors just allow to download new emualtors nowday. Anyway you can try to download some old emulator for the web.I advice you to use, it works great on Windows!
8) Open the build.gradle file (the "app module" one) and change MinSdkVersion from "14" to the SDK version of the android you're targeting. (You can quickly find the SDK version that corresponds to the old android version you're targeting in the "SDK manager" section;
9)REMOVE the new ANDROIDX dependencies from the build.gradle file (the "app module" on). You just need to comment the lines that contain AndroidX elements;
10)REMOVE the hardware acceleration ON parameter in the manifest file if the app you're backporting is giving you runtime problems;
11) Pay attention to the "styles.xml" file in res/values. It contanins .xml customization to themes. Themes are not supported in old Android versions. You can find your own solution if you want to implements themes, or you can just remove them deleting this .xml files;
12) When searching for Android development resources and tutorials use keywords like "2.2" or "2.3" in your queries, or make an advanced search showing results , you'll find everything;
13) On GitHub you can find libraries that allows to add new features to app that target old Android versions. For example,for one of my projects, I used a library called "NineOldAndroid" that allows to use Android animations on old Android versions;
14) Share the knowledge you acquired working on old android versions!
15) Support me for free using my social links :)
