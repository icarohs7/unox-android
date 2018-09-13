# unox-android
[![](
https://jitpack.io/v/icarohs7/unox-android.svg)](
https://jitpack.io/#icarohs7/unox-android)

## Description
Set of android libraries

## Adding to the project
````groovy
repositories {
    maven { url 'https://jitpack.io' }
}
dependencies {
    //All libs
    implementation "com.github.icarohs7:unox-android:$unoxandroid_version"
 
    //Single modules
    implementation "com.github.icarohs7.unox-android:telephony:$unoxandroid_version"
    implementation "com.github.icarohs7.unox-android:versioncontrol:$unoxandroid_version"
    implementation "com.github.icarohs7.unox-android:userinterface:$unoxandroid_version"
    implementation "com.github.icarohs7.unox-android:connectivity:$unoxandroid_version"
}
````
## How to use

### Telephony
Module to make phone calls without worrying about managing permissions <br/>
Before using, declare the permission at the manifest

* Kotlin
```kotlin
getTelephonyProvider()
        .callNumber(context,
                    phoneNumber,
                    askingMessage,
                    onDenyMessage)
```
* Java
```java
TelephonyModule
        .getTelephonyProvider()
        .callNumber(context,
                    phoneNumber,
                    askingMessage,
                    onDenyMessage);
```


### Version Control
Module to manage local vs remote versions of an app

* Initial Setup
First, define the functions that will provide the local and remote versions of the application <br/>
Define just once, the module is a Singleton and will save the definition as long as the app isn't destroyed
```kotlin
getVersionControlProvider().apply {
    localVersionProvider = { BuildConfig.VERSION_NAME }
    remoteVersionProvider = { VersionRepository.getLatestVersion() } /*Request latest version to your API*/
    // Both lambdas support suspension (coroutines)
}
```

* Using
```kotlin
getVersionControlProvider()
        .compareVersions { versionData ->
            val (oldVersion, newVersion, isUpdated) = versionData
            // This method gets the local version and the latest version
            // and wraps them in an object with a boolean with the comparation
            // between them
        }
```