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
    api(Deps.arrowCoreExtensions)
    api(Deps.arrowEffectsExtensions)
    api(Deps.arrowEffectsIoExtensions)
    api(Deps.arrowSyntax)
    api(Deps.arrowTypeclasses)
    api(Deps.gson)
    api(Deps.jodaTime)
    api(Deps.kodaTime)

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
    api(AndroidDeps.swipeRevealLayout)
    api(AndroidDeps.unoxAndroidArch)
    api(AndroidDeps.viewAnimator)

    debugApi(AndroidDeps.chuck)
    releaseApi(AndroidDeps.chuckNoOp)

    AndroidKaptDeps.core.forEach(::kapt)
}