rootProject.name = "unox-core"

include(":shared")
include(":jvm")

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("org.jetbrains.kotlin."))
                useVersion(Versions.kotlin)

            if (requested.id.id == "kotlin2js") {
                useVersion(Versions.kotlin)
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
            }
        }

        repositories {
            gradlePluginPortal()
            google()
            jcenter()
        }
    }
}

//enableFeaturePreview("GRADLE_METADATA")