package base.corelibrary

import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.CallSuper
import base.corelibrary.domain.toplevel.kget
import com.chuckerteam.chucker.api.ChuckerCollector
import com.facebook.stetho.Stetho
import com.github.icarohs7.unoxandroidarch.UnoxAndroidArch
import com.umutbey.stateviews.StateViewsBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import splitties.init.appCtx
import splitties.resources.drawable
import splitties.resources.str
import timber.log.Timber

abstract class BaseApplication : Application() {
    @CallSuper
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        setupKoin()
        setupThisLibrary()
        installUncaughtExceptionHandler()
        setupTimber()
        setupUnoxAndroidArch()
        lockOrientation()
        setupStateViews(getStateViewsBuilder())
    }


    private fun setupKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(module {
                single { ChuckerCollector(appCtx) }
            }) + onCreateKoinModules())
        }
    }

    private fun setupThisLibrary() {
        Corelibrary.log = { tag, msg -> Timber.tag(tag).i("$msg") }
    }

    private fun installUncaughtExceptionHandler() {
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Timber.tag("UncaughtException").e("""
                    Uncaught exception on thread ${thread.name}:
                    $throwable
                    ${throwable.stackTrace.joinToString(separator = "\n")}
                """.trimIndent())
            kget<ChuckerCollector>().onError("UncaughtException", throwable)
            defaultHandler?.uncaughtException(thread, throwable)
        }
    }

    open fun onCreateKoinModules(): List<Module> {
        return listOf(module {})
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupUnoxAndroidArch(): Unit = UnoxAndroidArch.run {
        init()
    }

    /**
     * Override to use another orientation
     * throughout the apllication.
     * List of option on [ActivityInfo].
     * Return null to use user defined
     * orientation
     */
    open fun onSelectOrientation(): Int? {
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun lockOrientation() {
        val orientation = onSelectOrientation() ?: return

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                activity.requestedOrientation = orientation
            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }
        })
    }

    open fun setupStateViews(builder: StateViewsBuilder) {
    }

    private fun getStateViewsBuilder(): StateViewsBuilder {
        return StateViewsBuilder.init().setState(
                tag = EMPTY_ADAPTER_STATE_TAG,
                title = str(R.string.nenhum_item_carregado),
                description = "",
                icon = drawable(R.drawable.ic_search_gray_24dp)
        )
                .setIconSize(120)
    }

    companion object {
        const val EMPTY_ADAPTER_STATE_TAG: String = "base_application_empty_adapter"
    }
}