package base.corelibrary.presentation

import androidx.appcompat.app.AppCompatActivity
import com.github.icarohs7.unoxandroidarch.extensions.startActivity
import com.github.icarohs7.unoxandroidarch.toplevel.onActivity
import kotlin.reflect.KClass

typealias ActivityClass = KClass<out AppCompatActivity>

object AppView {
    lateinit var SPLASH: ActivityClass
    lateinit var LOGIN: ActivityClass
    lateinit var MAIN: ActivityClass
}

object CoreNavigation {
    fun splashActivity() {
        onActivity { startActivity(AppView.SPLASH, finishActivity = true) }
    }

    fun loginActivity() {
        onActivity { startActivity(AppView.LOGIN, finishActivity = true) }
    }

    fun mainActivity() {
        onActivity { startActivity(AppView.MAIN, finishActivity = true) }
    }
}