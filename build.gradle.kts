plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("androidx.navigation.safeargs.kotlin")
    id("jacoco")
    defaults.`android-module`
}

android {
    defaultSettings(project)
    defaultConfig {
        consumerProguardFiles("proguard-rules.pro", "./proguard")
    }
}

dependencies {
    api(Deps.arrowCoreData)
    api(Deps.coroutinesCore)
    api(Deps.khronos)
    api(Deps.kotlinStdLib)
    api(Deps.kotlinxSerialization)
    api(Deps.okHttp)
    api(Deps.okHttpLoggingInterceptor)
    api(Deps.retrofit)
    api(Deps.retrofitKotlinxSerializationConverter)

    api(AndroidDeps.flashbar)
    api(AndroidDeps.flexboxLayout)
    api(AndroidDeps.fragmentKtx)
    api(AndroidDeps.googlePlayCore)
    api(AndroidDeps.koinAndroid)
    api(AndroidDeps.kotpref)
    api(AndroidDeps.kotprefInitializer)
    api(AndroidDeps.lifecycleLivedataKtx)
    api(AndroidDeps.lifecycleRuntimeKtx)
    api(AndroidDeps.materialComponents)
    api(AndroidDeps.materialEditText)
    api(AndroidDeps.mvRx)
    api(AndroidDeps.navigationFragmentKtx)
    api(AndroidDeps.navigationUiKtx)
    api(AndroidDeps.recyclerView)
    api(AndroidDeps.spinKit)
    api(AndroidDeps.splittiesAppctx)
    api(AndroidDeps.splittiesPermissions)
    api(AndroidDeps.splittiesResources)
    api(AndroidDeps.splittiesSystemservices)
    api(AndroidDeps.splittiesViews)
    api(AndroidDeps.stateViews)
    api(AndroidDeps.stetho)
    api(AndroidDeps.stethoOkHttp)
    api(AndroidDeps.swipeRefreshLayout)
    api(AndroidDeps.timber)
    api(AndroidDeps.unoxAndroidArchCore)

    debugApi(AndroidDeps.chucker)
    releaseApi(AndroidDeps.chuckerNoOp)
}

setupJacoco {
    sourceDirectories.setFrom(files(
            android.sourceSets["main"].java.srcDirs,
            "src/main/kotlin"
    ))
}

repositories {
    google()
    mavenLocal()
    mavenCentral()
    maven("https://kotlin.bintray.com/kotlinx")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://dl.bintray.com/icarohs7/libraries")
    jcenter()
    maven("https://jitpack.io")
}