@file:Suppress("UNUSED_VARIABLE")

plugins {
    kotlin("multiplatform")
    id("jacoco")
    id("maven-publish")
    id("com.jfrog.bintray")
    id("com.github.b3er.local.properties") version "1.1"
}

kotlin {
    metadata {
        mavenPublication { artifactId = "unoxcore-common" }
    }

    js {
        mavenPublication { artifactId = "unoxcore-common-js" }
    }

    jvm {
        mavenPublication { artifactId = "unoxcore-common-jvm" }
        compilations.all { kotlinOptions.jvmTarget = "1.6" }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                api(CommonDeps.coroutinesCoreCommon)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jsMain by getting {
            dependencies {
                api(kotlin("stdlib-js"))
                api(JSDeps.coroutinesJs)
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }

        val jvmMain by getting {
            dependencies {
                api(Deps.kotlinStdLib)
                api(Deps.arrowCore)
                api(Deps.arrowEffects)
                api(Deps.coroutinesCore)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                TestDeps.core.forEach(::implementation)
            }
        }
    }
}

setupJacoco {
    sourceDirectories.setFrom(files(
            "src/jvmMain/kotlin"
    ))
}

setupBintrayPublish(bintray) {
    setPublications("metadata", "js", "jvm")
}