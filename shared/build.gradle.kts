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
        mavenPublication { artifactId = "unoxcore-metadata" }
    }

    js {
        mavenPublication { artifactId = "unoxcore-js" }
    }

    jvm {
        mavenPublication { artifactId = "unoxcore-jvm" }
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.6"
            }
        }
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
                api(Deps.coroutinesRx2)
                api(Deps.rxKotlin)
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
            "src/main/kotlin"
    ))
}

setupBintrayPublish(bintray) {
    setPublications("metadata", "js", "jvm")
}