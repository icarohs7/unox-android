import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.delegateClosureOf
import com.android.build.gradle.LibraryExtension as AndroidBlock

fun Project.setupBintrayPublish(
        bintrayExtension: BintrayExtension,
        vararg publications: String = arrayOf(""),
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

    setPublications(*publications)
    pkg {
        desc = findProp("description")
        name = findProp("library_name")
        repo = findProp("repository")
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