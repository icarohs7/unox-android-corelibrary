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

    api(AndroidDeps.bluetoothKit)
    api(AndroidDeps.circularImageView)
    api(AndroidDeps.koinAndroidxViewmodel)
    api(AndroidDeps.kotprefGson)
    api(AndroidDeps.maskedEditText)
    api(AndroidDeps.materialDialogs)
    api(AndroidDeps.materialDialogsDateTime)
    api(AndroidDeps.materialDialogsInput)
    api(AndroidDeps.materialDrawer)
    api(AndroidDeps.materialDrawerKt)
    api(AndroidDeps.materialDrawerMaterialTypeface)
    api(AndroidDeps.materialDrawerMaterialTypefaceOriginal)
    api(AndroidDeps.materialEditText)
    api(AndroidDeps.navigationFragment)
    api(AndroidDeps.navigationUi)
    api(AndroidDeps.rxBinding)
    api(AndroidDeps.rxBindingAppCompat)
    api(AndroidDeps.rxBindingCore)
    api(AndroidDeps.rxBindingMaterial)
    api(AndroidDeps.rxBindingRecyclerView)
    api(AndroidDeps.splittiesLifecycleCoroutines)
    api(AndroidDeps.splittiesMainhandler)
    api(AndroidDeps.splittiesSnackbar)
    api(AndroidDeps.splittiesToast)
    api(AndroidDeps.unoxAndroidArch)
    api(AndroidDeps.viewAnimator)

    AndroidKaptDeps.core.forEach(::kapt)
}