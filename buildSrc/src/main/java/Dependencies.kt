object Deps {
    const val androidxcore: String = "androidx.core:core-ktx:${Versions.androidxcore}"
    const val ankoCommons: String = "org.jetbrains.anko:anko-commons:${Versions.anko}"
    const val ankoSdk25: String = "org.jetbrains.anko:anko-sdk25:${Versions.anko}"
    const val appCompat: String = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val arrowCore: String = "io.arrow-kt:arrow-core:${Versions.arrow}"
    const val bungee: String = "com.github.Binary-Finery:Bungee:${Versions.bungee}"
    const val coroutinesAndroid: String = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val fragmentKtx: String = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val glide: String = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val jsonIter: String = "com.jsoniter:jsoniter:${Versions.jsonIter}"
    const val kotlinStdLib: String = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val lifecycleExtensions: String = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val maskedEditText: String = "com.github.santalu:mask-edittext:${Versions.maskedEditText}"
    const val materialDesign: String = "com.google.android.material:material:${Versions.materialDesign}"
    const val materialDialogs: String = "com.afollestad.material-dialogs:core:${Versions.materialDialogs}"
    const val recyclerView: String = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val rxJava2: String = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
}

object KaptDeps {
    val core: List<String> = listOf(
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
    const val coroutines: String = "1.1.0"
    const val fragment: String = "1.0.0"
    const val glide: String = "4.8.0"
    const val jsonIter: String = "0.9.19"
    const val kotlin: String = "1.3.11"
    const val lifecycle: String = "2.0.0"
    const val maskedEditText: String = "1.1.1"
    const val materialDesign: String = "1.0.0"
    const val materialDialogs: String = "2.0.0-rc7"
    const val recyclerView: String = "1.0.0"
    const val rxJava: String = "2.2.5"

    const val barista: String = "2.7.1"
    const val fixd: String = "1.0.3"
    const val kotlinAssertUtils: String = "0.8.0"
    const val robolectric: String = "4.1"
    const val testCore: String = "1.1.0"

    const val espresso: String = "3.1.1"
    const val testRules: String = "1.1.1"
}