# unox-android
[![](
https://jitpack.io/v/icarohs7/unox-android.svg)](
https://jitpack.io/#icarohs7/unox-android)
[![Build Status](
https://travis-ci.org/icarohs7/unox-android.svg?branch=master)](
https://travis-ci.org/icarohs7/unox-android)
[![HitCount](
http://hits.dwyl.io/icarohs7/unox-android.svg)](
http://hits.dwyl.io/icarohs7/unox-android)
[![GitHub license](
https://img.shields.io/github/license/icarohs7/unox-android.svg)](
https://github.com/icarohs7/unox-android/blob/master/LICENSE)
[![GitHub issues](
https://img.shields.io/github/issues/icarohs7/unox-android.svg)](
https://github.com/icarohs7/unox-android/issues)
[![GitHub forks](
https://img.shields.io/github/forks/icarohs7/unox-android.svg)](
https://github.com/icarohs7/unox-android/network)
[![GitHub stars](
https://img.shields.io/github/stars/icarohs7/unox-android.svg)](
https://github.com/icarohs7/unox-android/stargazers)

## Description
Android library with a collection of modules and many features

## Disclaimer
* Beware when updating, breaking changes between updates happen very frequently
* Beware to the module dependencypull, it's used solely to pull a lot of dependencies

## Adding to the project

Groovy DSL
```groovy
repositories {
    maven { url "https://jitpack.io" }
}
dependencies {
    //All libs
    implementation "com.github.icarohs7:unox-android:$unoxandroid_version"

    //Single modules
    implementation "com.github.icarohs7.unox-android:<moduleName>:$unoxandroid_version"
}
```

Kotlin DSL
```kotlin
repositories {
    maven("https://jitpack.io")
}
dependencies {
    //All libs
    implementation("com.github.icarohs7:unox-android:$unoxandroid_version")

    //Single modules
    implementation("com.github.icarohs7.unox-android:<moduleName>:$unoxandroid_version")
}
```
### List of modules [here](https://github.com/icarohs7/unox-android/tree/master/settings.gradle) (without prefix colon)