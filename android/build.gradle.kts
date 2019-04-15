plugins {
    id("com.android.library")
    id("kotlin-android")
    id("jacoco")
    id("maven-publish")
    id("com.jfrog.bintray")
    id("com.github.b3er.local.properties") version "1.1"
    defaults.`android-module`
}

android {
    defaultSettings()
    sourceSets["main"].java.srcDir("src/main/kotlin")
    sourceSets["test"].java.srcDir("src/test/kotlin")

    dataBinding {
        isEnabled = false
    }

    buildTypes {
        getByName("debug") {
            isTestCoverageEnabled = true
        }
    }
}

dependencies {
    api("${findProp("group")}:unoxcore-jvm:${findProp("version")}")
    api(AndroidDeps.appCompat)
    api(AndroidDeps.coreKtx)
    api(AndroidDeps.coroutinesAndroid)
    api(AndroidDeps.disposer)
    api(AndroidDeps.lifecycleExtensions)
    api(AndroidDeps.lives)
    api(AndroidDeps.rxAndroid)

    TestDeps.androidCore.forEach {
        implementation(it) {
            exclude(group = "org.apache.maven")
        }
    }
}

setupJacoco {
    sourceDirectories.setFrom(files(
            android.sourceSets["main"].java.srcDirs,
            "src/main/kotlin"
    ))
}

setupAndroidPublication("kotlinAndroid", android, "unoxcore-android")

setupBintrayPublish(bintray) {
    setPublications("kotlinAndroid")
}