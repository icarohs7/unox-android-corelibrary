package base.corelibrary

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.CallSuper
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.gsonpref.gson
import com.facebook.stetho.Stetho
import com.github.icarohs7.unoxandroidarch.UnoxAndroidArch
import com.google.gson.Gson
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerUIUtils
import com.squareup.picasso.Picasso
import com.umutbey.stateviews.StateViewsBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import splitties.resources.drawable
import splitties.resources.str
import timber.log.Timber

abstract class BaseApplication : Application() {
    @CallSuper
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        setupKoin()
        setupTimber()
        setupUnoxAndroidArch()
        setupKotpref()
        lockOrientation()
        setupMaterialDrawerImageLoading()

        val stateViewsBuilder = StateViewsBuilder.init().setState(
                tag = EMPTY_ADAPTER_STATE_TAG,
                title = str(R.string.nenhum_item_carregado),
                description = "",
                icon = drawable(R.drawable.ic_search_gray_24dp)
        )
                .setIconSize(120)
        setupStateViews(stateViewsBuilder)
    }

    open fun setupStateViews(builder: StateViewsBuilder) {
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            modules(onCreateKoinModules())
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
        screenTransition = UnoxAndroidArch.AnimationType.FADE
    }

    private fun setupKotpref() {
        Kotpref.init(applicationContext)
        Kotpref.gson = Gson()
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

    private fun setupMaterialDrawerImageLoading() {
        DrawerImageLoader.init(object : AbstractDrawerImageLoader() {
            private val picasso by lazy { Picasso.get() }

            override fun placeholder(ctx: Context, tag: String?): Drawable {
                return DrawerUIUtils.getPlaceHolder(ctx)
            }

            override fun set(imageView: ImageView, uri: Uri, placeholder: Drawable?, tag: String?) {
                picasso.load(uri)
                        .also { placeholder?.let(it::placeholder) }
                        .into(imageView)
            }

            override fun cancel(imageView: ImageView) {
                picasso.cancelRequest(imageView)
            }
        })
    }

    companion object {
        const val EMPTY_ADAPTER_STATE_TAG: String = "base_application_empty_adapter"
    }
}