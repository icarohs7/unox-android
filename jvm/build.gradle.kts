plugins {
    kotlin("multiplatform")
    id("jacoco")
    id("maven-publish")
    id("com.jfrog.bintray")
    id("com.github.b3er.local.properties")
}

compileKotlin {
    useExperimentalFeatures()
}

kotlin {
    setupMetaInfoNameOnAll(rootProject, project)
    val artifact = "unoxcore-jvm"
    metadata { mavenPublication { artifactId = "$artifact-metadata" } }
    jvm {
        mavenPublication { artifactId = artifact }
        compilations.all { kotlinOptions.jvmTarget = "1.8" }
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val jvmMain by getting {
            kotlin.srcDir("src/main/kotlin")
            dependencies {
                api(project(":shared"))

                implementation(Deps.kotlinStdLib)
                implementation(Deps.arrowCoreData)
                implementation(Deps.coroutinesCore)
            }
        }

        val jvmTest by getting {
            kotlin.srcDir("src/test/kotlin")
            dependencies {
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

setupBintrayPublish(bintray, "metadata", "jvm")