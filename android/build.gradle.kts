plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("jacoco")
    id("maven-publish")
    id("com.jfrog.bintray")
    id("com.github.b3er.local.properties") version "1.1"
    defaults.`android-module`
}

android {
    defaultSettings()

    dataBinding {
        isEnabled = false
    }
}

kotlin {
    setupMetaInfoNameOnAll(rootProject, project)

    metadata { mavenPublication { artifactId = "unoxcore-android-metadata" } }

    android {
        mavenPublication { artifactId = "unoxcore-android" }
        publishLibraryVariants("debug")
        compilations.all { kotlinOptions.jvmTarget = "1.6" }
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val androidMain by getting {
            kotlin.srcDir("src/main/kotlin")
            dependencies {
                api(project(":jvm"))
                api(AndroidDeps.appCompat)
                api(AndroidDeps.coroutinesAndroid)
                api(AndroidDeps.disposer)
                api(AndroidDeps.lifecycleReactiveStreamsKtx)
                api(AndroidDeps.rxAndroid)
            }
        }

        val androidTest by getting {
            dependsOn(androidMain)
            kotlin.srcDir("src/test/kotlin")
            dependencies {
                implementation(AndroidDeps.coreKtx)
                TestDeps.androidCore.forEach {
                    implementation(it) {
                        exclude(group = "org.apache.maven")
                    }
                }
            }
        }
    }
}

setupJacoco {
    sourceDirectories.setFrom(files(
            android.sourceSets["main"].java.srcDirs,
            "src/main/kotlin"
    ))
}

setupBintrayPublish(bintray, "metadata", "androidDebug")