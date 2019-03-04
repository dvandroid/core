object Versions {

    /**
     * attached reference for versioning the android apps and it's build format
     * https://droidmentor.com/versioning-an-android-app-how-and-why/
     */

    ////////////////////////////////////////////////////////////////////////////////////////////
    // VERSION                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////


    internal var major = 1
    internal var minor = 0
    internal var patch = 0

    internal var preRelease = "DEMO"

    ////////////////////////////////////////////////////////////////////////////////////////////
    // BUILD                                                                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////

    internal const val minSdk = 21
    internal const val targetSdk = 28

    internal var gradle = "3.2.0"
    internal var kotlin = "1.3.20"

    ////////////////////////////////////////////////////////////////////////////////////////////
    // MAIN                                                                                   //
    ////////////////////////////////////////////////////////////////////////////////////////////

    internal var support = "28.0.0"
    internal var constraintLayout = "1.1.0"
    internal var dagger = "2.13"
    internal var rxAndroid = "2.0.1"
    internal var rxJava = "2.1.7"
    internal var retrofit = "2.4.0"
    internal var okHttp = "3.9.0"
    internal var moshi = "1.5.0"
    internal var kotshi = "1.0.5"
    internal var roomDatabaseVersion = "1.0.0"



    ////////////////////////////////////////////////////////////////////////////////////////////
    // OTHERS                                                                                 //
    ////////////////////////////////////////////////////////////////////////////////////////////



    internal var sdp = "1.0.5"
    internal var jvm = "0.0.4"
    internal var zxing = "1.9.8"
    internal var timber = "4.7.1"

    ////////////////////////////////////////////////////////////////////////////////////////////
    // TESTING                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////

    internal var jUnit = "4.12"
    internal var testRunner = "1.0.2"
    internal var testEspresso = "3.0.2"

}


object AppDeps {

    var appId = "core.android.com.android_core"

    var versionCode = generateVersionCode()
    var versionName = generateVersionName()

    var buidleGradle = "com.android.tools.build:gradle:${Versions.gradle}"

    var kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    var kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

}

object SupportDeps {
    var compat = "com.android.support:appcompat-v7:${Versions.support}"
    var compatv13 = "com.android.support:support-v13:${Versions.support}"
    var app = "com.android.support:support-compat:${Versions.support}"
    var cardView = "com.android.support:cardview-v7:${Versions.support}"
    var recyclerView = "com.android.support:recyclerview-v7:${Versions.support}"
    var design = "com.android.support:design:${Versions.support}"
    var constraintLayout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}"

}

object DaggerDeps {

    var dagger = "com.google.dagger:dagger:${Versions.dagger}"
    var compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    var android = "com.google.dagger:dagger-android:${Versions.dagger}"
    var support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    var processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

}

object RetrofitDeps {
    var retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    var gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    var moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    var rx = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
}

object OkHttpDeps {
    var okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    var logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
}

object GsonConverterDeps {
    var moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    var kotshi = "se.ansman.kotshi:api:${Versions.kotshi}"
    var compiler = "se.ansman.kotshi:compiler:${Versions.kotshi}"
}

object RxDeps {
    var android = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    var java = "io.reactivex.rxjava2:rxjava:${Versions.rxAndroid}"
}

object Database{
    var roomrx =  "android.arch.persistence.room:rxjava2:${Versions.roomDatabaseVersion}"
    var compiler =  "android.arch.persistence.room:compiler:${Versions.roomDatabaseVersion}"
}

object OtherDeps {
    var sdp = "com.intuit.sdp:sdp-android:${Versions.sdp}"
    var jvm = "io.github.jffiorillo:jvmbuilder-annotations:${Versions.jvm}"
    var kjvm = "io.github.jffiorillo:jvmbuilder:${Versions.jvm}"
    var zxing = "me.dm7.barcodescanner:zbar:${Versions.zxing}"
    var timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestingDeps {

    var jUnit = "junit:junit:${Versions.jUnit}"
    var testRunner = "com.android.support.test:runner:${Versions.testRunner}"
    var testEspresso = "com.android.support.test.espresso:espresso-core:${Versions.testEspresso}"

}

fun generateVersionCode(): Int = Versions.minSdk * 10000000 + Versions.major * 10000 + Versions.minor * 100 + Versions.patch

fun generateVersionName(): String {
    val versionName = "${Versions.major}.${Versions.minor}.${Versions.patch}"

    if (Versions.preRelease.isNotEmpty()) {
        versionName.plus("-${Versions.preRelease}")
    }

    return versionName

}

/**
 * TODO: Still RND For Kotlin Gradle DSL if they can apply function in gradle like groovy.
 */
fun generateSuffix(buildtype: AppBuildType): String {
    return when (buildtype) {
        is AppBuildType.DEV -> "DEV"
        is AppBuildType.QA -> "QA"
        is AppBuildType.STAGING -> "STAGING"
    }
}

sealed class AppBuildType {

    object DEV : AppBuildType()
    object QA : AppBuildType()
    object STAGING : AppBuildType()

}

