rootProject.name = "unox-core"

include(":shared")

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

//TODO re-enable when https://youtrack.jetbrains.com/issue/KT-29758 is fixed
enableFeaturePreview("GRADLE_METADATA")