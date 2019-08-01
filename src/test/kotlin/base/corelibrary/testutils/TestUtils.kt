package base.corelibrary.testutils

import android.os.Looper
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import arrow.core.Tuple2
import arrow.core.Tuple3
import com.github.icarohs7.unoxandroidarch.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.robolectric.Robolectric
import org.robolectric.Shadows
import org.robolectric.android.controller.ActivityController
import org.robolectric.shadows.ShadowLooper
import kotlin.reflect.full.createInstance

val shadowOfMainLooper: ShadowLooper get() = Shadows.shadowOf(Looper.getMainLooper())
fun runAllMainLooperMessages() {
    val mainLooper = shadowOfMainLooper
    if (!mainLooper.isIdle)
        mainLooper.idle()
}

inline fun <reified T : Fragment> mockFragment(): Tuple3<ActivityController<AppCompatActivity>, AppCompatActivity, T> {
    val (controller, act) = mockActivity<AppCompatActivity>()
    val fragment = T::class.createInstance()
    act.supportFragmentManager.commit { replace(10, fragment) }

    return Tuple3(controller, act, fragment)
}

inline fun <reified T : AppCompatActivity> mockActivity(): Tuple2<ActivityController<T>, T> {
    val controller = Robolectric.buildActivity(T::class.java)
    val act = controller.get()
    act.setTheme(R.style.Theme_AppCompat)
    controller.create()
    val layout = FrameLayout(act).apply { id = 10 }
    act.setContentView(layout)

    return Tuple2(controller, act)
}

inline fun <reified T : Fragment> testFragment(): T {
    val (controller, _, fragment) = mockFragment<T>()
    controller.resume()
    runBlocking { delay(500) }
    return fragment
}