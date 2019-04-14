import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import kotlin.collections.set
import com.android.build.gradle.TestedExtension as AndroidBlock
import org.gradle.api.artifacts.dsl.DependencyHandler as DependenciesBlock
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope as PluginsBlock

fun AndroidBlock.defaultSettings() {
    compileSdkVersion(28)

    facebookAppId = ""
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(28)
        testInstrumentationRunnerArguments.plusAssign("clearPackageData" to "true")
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    dataBinding {
        isEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        unitTests.apply {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    lintOptions {
        isAbortOnError = false
    }

    packagingOptions {
        pickFirst("META-INF/library_release.kotlin_module")
        pickFirst("META-INF/lib_release.kotlin_module")
    }
}

var AndroidBlock.facebookAppId: String
    get() = defaultConfig.manifestPlaceholders["facebookAppId"]?.toString().orEmpty()
    set(value) {
        defaultConfig.manifestPlaceholders["facebookAppId"] = value
    }

fun Project.setupJacoco(block: JacocoReport.() -> Unit = {}): Unit = tasks {
    configure<JacocoPluginExtension> {
        toolVersion = "0.8.3"
    }

    withType<Test> {
        extensions.getByType<JacocoTaskExtension>().setIncludeNoLocationClasses(true)
    }

    create<JacocoReport>("jacocoTestReport") {
        dependsOn("testDebugUnitTest", "check", "createDebugCoverageReport")

        this.group = "Reporting"
        this.description = "Generate Jacoco coverage reports for build"

        reports {
            xml.isEnabled = true
            html.isEnabled = true
        }

        val excludes = listOf(
                "**/R.class",
                "**/R\$*.class",
                "**/*\$ViewInjector*.*",
                "**/BuildConfig.*",
                "**/Manifest*.*",
                "**/*Test*.*",
                "android/**/*.*"
        )

        classDirectories.setFrom(
                fileTree("$buildDir/intermediates/classes/debug") { exclude(excludes) },
                fileTree("$buildDir/tmp/kotlin-classes/debug") { exclude(excludes) }
        )

        executionData("$buildDir/jacoco/*.exec")
        block()
    }

}.let { Unit }

fun Project.findProp(s: String) = project.findProperty(s) as String