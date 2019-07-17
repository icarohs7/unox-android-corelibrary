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
    api(Deps.arrowSyntax)
    api(Deps.coroutinesReactive)
    api(Deps.coroutinesRx2)
    api(Deps.jodaTime)
    api(Deps.khronos)
    api(Deps.kodaTime)
    api(Deps.kotlinStdLib)
    api(Deps.kotlinxSerialization)
    api(Deps.okHttp)
    api(Deps.okHttpLoggingInterceptor)
    api(Deps.retrofit)
    api(Deps.retrofitKotlinxSerializationConverter)
    api(Deps.rxKotlin)
    api(Deps.rxRelay)

    api(AndroidDeps.disposer)
    api(AndroidDeps.drawableToolbox)
    api(AndroidDeps.flashbar)
    api(AndroidDeps.flexboxLayout)
    api(AndroidDeps.fragmentKtx)
    api(AndroidDeps.googlePlayCore)
    api(AndroidDeps.koinAndroid)
    api(AndroidDeps.kotpref)
    api(AndroidDeps.kotprefInitializer)
    api(AndroidDeps.lifecycleLivedataKtx)
    api(AndroidDeps.lifecycleReactiveStreamsKtx)
    api(AndroidDeps.lifecycleRuntimeKtx)
    api(AndroidDeps.materialComponents)
    api(AndroidDeps.materialDialogs)
    api(AndroidDeps.materialEditText)
    api(AndroidDeps.mvRx)
    api(AndroidDeps.navigationFragmentKtx)
    api(AndroidDeps.navigationUiKtx)
    api(AndroidDeps.picasso)
    api(AndroidDeps.pugNotification)
    api(AndroidDeps.quantum)
    api(AndroidDeps.quantumRx)
    api(AndroidDeps.recyclerView)
    api(AndroidDeps.roomKtx)
    api(AndroidDeps.roomRxJava2)
    api(AndroidDeps.spinKit)
    api(AndroidDeps.splittiesAppctx)
    api(AndroidDeps.splittiesMainhandler)
    api(AndroidDeps.splittiesPermissions)
    api(AndroidDeps.splittiesResources)
    api(AndroidDeps.splittiesSystemservices)
    api(AndroidDeps.splittiesViews)
    api(AndroidDeps.spotsDialog)
    api(AndroidDeps.stateViews)
    api(AndroidDeps.stetho)
    api(AndroidDeps.stethoOkHttp)
    api(AndroidDeps.timber)
    api(AndroidDeps.unoxAndroidArchCore)
    api(AndroidDeps.viewAnimator)

    debugApi(AndroidDeps.chucker)
    releaseApi(AndroidDeps.chuckerNoOp)

    AndroidKaptDeps.core.forEach(::kapt)
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
    maven("http://devrepo.kakao.com:8088/nexus/content/groups/public/")
    maven("https://kotlin.bintray.com/kotlinx")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://dl.bintray.com/icarohs7/libraries")
    jcenter()
    maven("https://jitpack.io")
}