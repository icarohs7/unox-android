object Deps { //TODO collector method
    const val kotlinStdLib: String = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutines: String = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val ankoCommons: String = "org.jetbrains.anko:anko-commons:${Versions.anko}"
    const val ankoSdk25: String = "org.jetbrains.anko:anko-sdk25:${Versions.anko}"
    const val spinkit: String = "com.github.ybq:Android-SpinKit:${Versions.spinkit}"
    const val recyclerView: String = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val materialDesign: String = "com.google.android.material:material:${Versions.materialDesign}"
    const val fragment: String = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val constraintLayout: String = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycleExtensions: String = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val appCompat: String = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val androidxCore: String = "androidx.core:core-ktx:${Versions.androidxcore}"
    const val glide: String = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideKapt: String = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val maskedEditText: String = "com.github.santalu:mask-edittext:${Versions.maskedEditText}"
    const val bungee: String = "com.github.Binary-Finery:Bungee:${Versions.bungee}"
    const val rxJava: String = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val arrowCore: String = "io.arrow-kt:arrow-core:${Versions.arrow}"
    const val kotlinReflection: String = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

    /** testImplementation */
    const val kotlinAssertUtils: String = "se.lovef:kotlin-assert-utils:${Versions.kotlinassertutils}"
    /** testImplementation */
    const val robolectric: String = "org.robolectric:robolectric:${Versions.robolectric}"
    /** androidTestImplementation */
    const val testRunner: String = "androidx.test:runner:${Versions.testRunner}"
    /** androidTestImplementation */
    const val espresso: String = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object Versions { //TODO clean up
    const val kotlin: String = "1.3.0"
    const val coroutines: String = "1.0.1"
    const val anko: String = "0.10.8"
    const val spinkit: String = "1.1.0"
    const val recyclerView: String = "1.0.0"
    const val materialDesign: String = "1.0.0"
    const val fragment: String = "1.0.0"
    const val constraintLayout: String = "1.1.3"
    const val lifecycle: String = "2.0.0"
    const val appCompat: String = "1.0.2"
    const val androidxcore: String = "1.0.1"
    const val glide: String = "4.8.0"
    const val maskedEditText: String = "1.0.9"
    const val bungee: String = "master-SNAPSHOT"
    const val rxJava: String = "2.2.3"
    const val arrow: String = "0.8.0"
    const val kotlinassertutils: String = "0.8.0"
    const val robolectric: String = "4.0.1"
    const val testRunner: String = "1.1.0"
    const val espresso: String = "3.1.0"
}