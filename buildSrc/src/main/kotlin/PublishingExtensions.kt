import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.internal.artifacts.dependencies.AbstractModuleDependency
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.delegateClosureOf
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByName
import java.io.File
import com.android.build.gradle.LibraryExtension as AndroidBlock

fun Project.setupBintrayPublish(
        bintrayExtension: BintrayExtension,
        block: BintrayExtension.() -> Unit = {}
): Unit = with(bintrayExtension) {
    try {
        user = project.findProp("bintray_user")
        key = project.findProp("bintray_api_key")
    } catch (e: TypeCastException) {
        return@with
    }
    publish = true
    override = true

    setConfigurations("archives")
    pkg {
        desc = findProp("description")
        repo = findProp("repository")
        name = findProp("library_name")
        githubRepo = findProp("githubRepo")
        websiteUrl = findProp("websiteUrl")
        vcsUrl = findProp("vcsUrl")
        issueTrackerUrl = findProp("issueTrackerUrl")
        publicDownloadNumbers = true
        setLabels("kotlin")
        setLicenses("MIT")
    }

    block()
}

fun BintrayExtension.pkg(block: BintrayExtension.PackageConfig.() -> Unit) {
    pkg(delegateClosureOf(block))
}

fun Project.setupKotlinJvmPublication(
        name: String,
        sources: SourceSetContainer,
        artifact: String
) {
    with(tasks) {
        create<Jar>("sourcesJar") {
            archiveClassifier.set("sources")
            from(sources["main"].allSource)
        }

        create<Jar>("javadocJar") {
            dependsOn("javadoc")
            archiveClassifier.set("javadoc")
            from(getByName<Javadoc>("javadoc").destinationDir)
        }
    }

    setupPublication(name, artifact) {
        from(components["java"])
        artifact(tasks["sourcesJar"])
        artifact(tasks["javadocJar"])
    }
}

fun Project.setupAndroidPublication(name: String, android: AndroidBlock, artifact: String): Unit = afterEvaluate {
    with(tasks) {
        create<Jar>("sourcesJar") {
            archiveClassifier.set("sources")
            from(android.sourceSets["main"].java.srcDirs)
        }

        create<Javadoc>("javadoc") {
            source = fileTree(android.sourceSets["main"].java.srcDirs)
            classpath += files(android.bootClasspath.joinToString(File.pathSeparator))
            android.libraryVariants.forEach { variant ->
                if (variant.name == "release") {
                    classpath += variant.javaCompileProvider.get().classpath
                }
            }
            exclude("**/R.html", "**/R.*.html", "**/index.html")
        }

        create<Jar>("javadocJar") {
            dependsOn("javadoc")
            archiveClassifier.set("javadoc")
            from(getByName<Javadoc>("javadoc").destinationDir)
        }
    }

    setupPublication(name, artifact) { pom ->
        pom.packaging = "aar"
        artifact(tasks["bundleDebugAar"])
        artifact(tasks["sourcesJar"])
        artifact(tasks["javadocJar"])
    }
}

fun Project.setupPublication(name: String, artifact: String, block: MavenPublication.(MavenPom) -> Unit = {}) {
    configure<PublishingExtension> {
        publications {
            create<MavenPublication>(name) {
                setupPom(artifact, project) {
                    withXml {
                        val dependenciesNode = asNode().appendNode("dependencies")
                        fun addDependency(dep: Dependency, scope: String) {

                            if (dep.group == null || dep.version == null || dep.name == "unspecified")
                                return

                            val dependencyNode = dependenciesNode.appendNode("dependency").apply {
                                appendNode("groupId", dep.group)
                                appendNode("artifactId", dep.name)
                                appendNode("version", dep.version)
                                appendNode("scope", scope)
                            }

                            if (dep !is AbstractModuleDependency) return

                            if (!dep.isTransitive) {
                                with(dependencyNode.appendNode("exclusions").appendNode("exclusion")) {
                                    appendNode("groupId", "*")
                                    appendNode("artifactId", "*")
                                }
                            } else if (dep.excludeRules.isNotEmpty()) {
                                with(dependencyNode.appendNode("exclusions").appendNode("exclusion")) {
                                    dep.excludeRules.forEach { rule ->
                                        appendNode("groupId", rule.group ?: "*")
                                        appendNode("artifactId", rule.module ?: "*")
                                    }
                                }
                            }
                        }

                        configurations["compile"].dependencies.forEach { dep -> addDependency(dep, "compile") }
                        configurations["api"].dependencies.forEach { dep -> addDependency(dep, "compile") }
                        configurations["implementation"].dependencies.forEach { dep -> addDependency(dep, "runtime") }

                        block(this@setupPom)
                    }
                }
            }
        }
    }
}

fun MavenPublication.setupPom(artifact: String, project: Project, block: MavenPom.() -> Unit = {}) = pom {
    name.set(project.findProp("name"))
    description.set(project.findProp("description"))
    url.set(project.findProp("websiteUrl"))
    groupId = project.findProp("group")
    artifactId = artifact
    version = project.findProp("version")
    licenses {
        license {
            name.set("MIT License")
            url.set("http://www.opensource.org/licenses/mit-license.php")
        }
    }
    developers {
        developer {
            id.set("icarohs7")
            name.set("Icaro R D Temponi")
            email.set("icarohs7@gmail.com")
        }
    }
    scm {
        connection.set(project.findProp("vcsUrl"))
        developerConnection.set(project.findProp("vcsUrl"))
        url.set(project.findProp("websiteUrl"))
    }
    block()
}