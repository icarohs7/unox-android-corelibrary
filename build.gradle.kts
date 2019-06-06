plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("androidx.navigation.safeargs.kotlin")
    defaults.`android-module`
}

android {
    defaultSettings()
}

dependencies {
    api(Deps.gson)
    api(Deps.jodaTime)
    api(Deps.kodaTime)
    api(Deps.kotlinxSerialization)
    api(Deps.okHttp)
    api(Deps.okHttpLoggingInterceptor)
    api(Deps.retrofit)
    api(Deps.retrofitKotlinxSerializationConverter)

    api(AndroidDeps.circularImageView)
    api(AndroidDeps.flexboxLayout)
    api(AndroidDeps.iconicsFontAwesome)
    api(AndroidDeps.iconicsMaterial)
    api(AndroidDeps.iconicsMaterialOriginal)
    api(AndroidDeps.kotprefGson)
    api(AndroidDeps.maskedEditText)
    api(AndroidDeps.materialDialogsDateTime)
    api(AndroidDeps.materialDialogsInput)
    api(AndroidDeps.materialDrawer)
    api(AndroidDeps.materialDrawerKt)
    api(AndroidDeps.materialEditText)
    api(AndroidDeps.navigationFragmentKtx)
    api(AndroidDeps.navigationUiKtx)
    api(AndroidDeps.splittiesLifecycleCoroutines)
    api(AndroidDeps.splittiesSnackbar)
    api(AndroidDeps.splittiesToast)
    api(AndroidDeps.stetho)
    api(AndroidDeps.stethoOkHttp)
    api(AndroidDeps.swipeRevealLayout)
    api(AndroidDeps.unoxAndroidArchCore)
    api(AndroidDeps.viewAnimator)

    debugApi(AndroidDeps.chuck)
    releaseApi(AndroidDeps.chuckNoOp)

    AndroidKaptDeps.core.forEach(::kapt)
}