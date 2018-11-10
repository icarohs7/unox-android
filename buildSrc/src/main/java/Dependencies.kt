object Deps {
    const val kotlinStdLib: String = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutines: String = ""
    const val anko: String = ""
    const val spinkit: String = ""
    const val recyclerView: String = ""
    const val materialDesign: String = ""
    const val fragment: String = ""
    const val constraintLayout: String = ""
    const val lifecycle: String = ""
    const val appCompat: String = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val androidxCore: String = ""
    const val glide: String = ""
    const val maskedEditText: String = ""
    const val bungee: String = ""
    const val rxJava: String = ""
    const val arrow: String = ""

    /** testImplementation */
    const val kotlinAssertUtils: String = "se.lovef:kotlin-assert-utils:${Versions.kotlinassertutils}"
    /** testImplementation */
    const val robolectric: String = "org.robolectric:robolectric:${Versions.robolectric}"
    /** androidTestImplementation */
    val testRunner = "androidx.test:runner:${Versions.testRunner}"
    /** androidTestImplementation */
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object Versions {
    const val kotlin: String = "1.3.0"
    const val coroutines: String = "1.0.1"
    const val anko: String = "0.10.8"
    const val spinkit: String = "1.1.0"
    const val recyclerview: String = "1.0.0"
    const val materialdesign: String = "1.0.0"
    const val fragment: String = "1.0.0"
    const val constraintlayout: String = "1.1.3"
    const val lifecycle: String = "2.0.0"
    const val appCompat: String = "1.0.2"
    const val androidxcore: String = "1.0.1"
    const val glide: String = "4.8.0"
    const val maskededittext: String = "1.0.9"
    const val bungee: String = "master-SNAPSHOT"
    const val rxjava: String = "2.2.3"
    const val arrow: String = "0.8.0"
    const val kotlinassertutils: String = "0.8.0"
    const val robolectric: String = "4.0.1"
    const val testRunner: String = "1.1.0"
    const val espresso: String = "3.1.0"
}