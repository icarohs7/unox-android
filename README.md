# unox-core

[![Kotlin](https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Kotlin-logo.svg/240px-Kotlin-logo.svg.png)](
https://kotlinlang.org)

[![Uses badges](https://forthebadge.com/images/badges/uses-badges.svg)](
https://forthebadge.com/)
[![Not a Lie](https://forthebadge.com/images/badges/its-not-a-lie-if-you-believe-it.svg)](
https://forthebadge.com)

[![Jcenter](https://api.bintray.com/packages/icarohs7/libraries/unox-core/images/download.svg)](
https://bintray.com/icarohs7/libraries/unox-core/_latestVersion)
[![GitHub top language](https://img.shields.io/github/languages/top/icarohs7/unox-core.svg)](
https://github.com/icarohs7/unox-core/search?l=kotlin)
[![HitCount](http://hits.dwyl.io/icarohs7/unox-core.svg)](
http://hits.dwyl.io/icarohs7/unox-core)
[![GitHub license](https://img.shields.io/github/license/icarohs7/unox-core.svg)](
https://github.com/icarohs7/unox-core/blob/master/LICENSE)
[![codecov](https://codecov.io/gh/icarohs7/unox-core/branch/master/graph/badge.svg)](
https://codecov.io/gh/icarohs7/unox-core)

[![GitHub commit activity](https://img.shields.io/github/commit-activity/w/icarohs7/unox-core.svg)](
https://github.com/icarohs7/unox-core/commits/master)
[![GitHub issues](https://img.shields.io/github/issues/icarohs7/unox-core.svg)](
https://github.com/icarohs7/unox-core/issues)
[![GitHub tag](https://img.shields.io/github/tag/icarohs7/unox-core.svg)](
https://github.com/icarohs7/unox-core/releases)
[![GitHub forks](https://img.shields.io/github/forks/icarohs7/unox-core.svg?style=social&label=Fork)](
https://github.com/icarohs7/unox-core/fork)
[![GitHub stars](https://img.shields.io/github/stars/icarohs7/unox-core.svg?style=social&label=Stars)](
https://github.com/icarohs7/unox-core)
[![GitHub watchers](https://img.shields.io/github/watchers/icarohs7/unox-core.svg?style=social&label=Watch)](
https://github.com/icarohs7/unox-core/subscription)

[![GitHub commits](https://img.shields.io/github/commits-since/icarohs7/unox-core/v0.1.0.svg)](
https://github.com/icarohs7/unox-core/releases/v0.1.0)
[![Awesome Badges](https://img.shields.io/badge/badges-awesome-green.svg)](
https://github.com/Naereen/badges)
[![BADGINATOR](https://badginator.herokuapp.com/icarohs7/unox-core.svg)](
https://github.com/defunctzombie/badginator)
[![Open Source Love svg2](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](
https://github.com/ellerbrock/open-source-badges/)
[![GitHub last commit](https://img.shields.io/github/last-commit/icarohs7/unox-core.svg)](
https://github.com/icarohs7/unox-core/commits/master)

## Adding to the project

```kotlin
repositories {
    jcenter()
}
dependencies {
    //Artifacts of common module
    implementation("com.github.icarohs7:unoxcore-common:$unoxcore_version") //Multiplatform
    implementation("com.github.icarohs7:unoxcore-common-js:$unoxcore_version") //JS
    implementation("com.github.icarohs7:unoxcore-common-jvm:$unoxcore_version") //JVM
    
    //Common module + JVM specific features
    implementation("com.github.icarohs7:unoxcore-jvm:$unoxcore_version")
}
```
