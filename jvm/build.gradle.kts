plugins {
    kotlin("jvm")
    id("jacoco")
    id("maven-publish")
    id("com.jfrog.bintray")
    id("com.github.b3er.local.properties") version "1.1"
}

dependencies {
    api("${findProp("group")}:unoxcore-common-jvm:${findProp("version")}")
    api(Deps.kotlinStdLib)
    api(Deps.arrowCore)
    api(Deps.arrowEffects)
    api(Deps.coroutinesRx2)
    api(Deps.rxKotlin)

    TestDeps.core.forEach(::implementation)
}


setupJacoco {
    sourceDirectories.setFrom(files(
            "src/main/kotlin"
    ))
}

setupKotlinJvmPublication("kotlinJvm", sourceSets["main"].allJava, "unoxcore-jvm")

setupBintrayPublish(bintray) {
    setPublications("kotlinJvm")
}