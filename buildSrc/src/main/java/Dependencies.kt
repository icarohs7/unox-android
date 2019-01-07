object Deps {
    val core: List<String> = listOf(
            "androidx.appcompat:appcompat:${Versions.appCompat}",
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}",
            "androidx.core:core-ktx:${Versions.androidxcore}",
            "androidx.fragment:fragment-ktx:${Versions.fragment}",
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}",
            "androidx.recyclerview:recyclerview:${Versions.recyclerView}",
            "com.github.Binary-Finery:Bungee:${Versions.bungee}",
            "com.github.bumptech.glide:glide:${Versions.glide}",
            "com.github.santalu:mask-edittext:${Versions.maskedEditText}",
            "com.github.ybq:Android-SpinKit:${Versions.spinkit}",
            "com.google.android.material:material:${Versions.materialDesign}",
            "com.louiscad.splitties:splitties-lifecycle-coroutines:${Versions.splitties}",
            "io.arrow-kt:arrow-core:${Versions.arrow}",
            "io.reactivex.rxjava2:rxjava:${Versions.rxJava}",
            "org.jetbrains.anko:anko-commons:${Versions.anko}",
            "org.jetbrains.anko:anko-sdk25:${Versions.anko}",
            "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}",
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    )
}

object KaptDeps {
    val core = listOf(
            "com.github.bumptech.glide:compiler:${Versions.glide}"
    )
}

object TestDeps {
    val core: List<String> = listOf(
            "androidx.test:core:${Versions.testCore}",
            "org.bigtesting:fixd:${Versions.fixd}",
            "org.robolectric:robolectric:${Versions.robolectric}",
            "se.lovef:kotlin-assert-utils:${Versions.kotlinAssertUtils}"
    )
}

object AndroidTestDeps {
    val core: List<String> = listOf(
            "androidx.test.espresso:espresso-contrib:${Versions.espresso}",
            "androidx.test.espresso:espresso-intents:${Versions.espresso}",
            "androidx.test:rules:${Versions.testRules}"
    ) + TestDeps.core.filterNot { it.contains("org.robolectric:robolectric") }
}

object Versions {
    const val androidxcore: String = "1.0.1"
    const val anko: String = "0.10.8"
    const val appCompat: String = "1.0.2"
    const val arrow: String = "0.8.1"
    const val bungee: String = "master-SNAPSHOT"
    const val constraintLayout: String = "1.1.3"
    const val coroutines: String = "1.1.0"
    const val fragment: String = "1.0.0"
    const val glide: String = "4.8.0"
    const val kotlin: String = "1.3.11"
    const val lifecycle: String = "2.0.0"
    const val maskedEditText: String = "1.1.1"
    const val materialDesign: String = "1.0.0"
    const val recyclerView: String = "1.0.0"
    const val rxJava: String = "2.2.5"
    const val spinkit: String = "1.2.0"
    const val splitties: String = "3.0.0-alpha02"

    const val barista: String = "2.7.1"
    const val fixd: String = "1.0.3"
    const val kotlinAssertUtils: String = "0.8.0"
    const val robolectric: String = "4.1"
    const val testCore: String = "1.1.0"

    const val espresso: String = "3.1.1"
    const val testRules: String = "1.1.1"
}