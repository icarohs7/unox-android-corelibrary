@file:JvmName("TopLevel")

package base.corelibrary.domain

import android.app.Activity
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.os.Bundle
import android.text.Spanned
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import arrow.core.Try
import base.corelibrary.R
import base.corelibrary.presentation.main.BaseMainActivity
import com.andrognito.flashbar.Flashbar
import com.github.icarohs7.unoxandroidarch.Injector
import com.github.icarohs7.unoxandroidarch.Messages
import com.github.icarohs7.unoxandroidarch.onActivity
import org.koin.core.get
import splitties.init.appCtx
import splitties.resources.appColor
import timber.log.Timber

/**
 * Short hand syntax to fetch an instance from
 * the DI container
 */
inline fun <reified T : Any> kget(): T = Injector.get()

/**
 * Helper used to navigate to another
 * navigation destination or action
 */
fun navigate(
        dest: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
) {
    val options = navOptions ?: androidx.navigation.navOptions {
        anim {
            enter = R.anim.enter_animation
            exit = R.anim.exit_animation
            popEnter = R.anim.enter_animation
            popExit = R.anim.exit_animation
        }
    }
    onActivity<BaseMainActivity> {
        Try { navController.navigate(dest, args, options, navigatorExtras) }
                .fold(Timber.tag("Navigation")::e) { Timber.tag("Navigation").i("$it") }
    }
}

/** Show a flashbar snackbar with a yellow gradient background */
fun showWarningFlashBar(
        message: String = "",
        duration: Int = 1500,
        gravity: Flashbar.Gravity = Flashbar.Gravity.TOP,
        context: Activity? = null
) {
    fun messageBuilder(act: Activity) {
        Messages.flashBar(act, message, duration, gravity) { backgroundDrawable(R.drawable.bg_gradient_yellow) }
    }
    context?.let(::messageBuilder) ?: onActivity(::messageBuilder)
}

/** Show a flashbar snack with the default background and bottom gravity */
fun showFlashSnackbar(message: String, duration: Int = 1000, context: Activity? = null) {
    fun messageBuilder(act: Activity) {
        Messages.flashBar(act, message, duration, Flashbar.Gravity.BOTTOM)
    }
    context?.let(::messageBuilder) ?: onActivity(::messageBuilder)
}

/** Current orientation of the phone */
val appOrientation: Int
    get() = appCtx.resources.configuration.orientation

/** Whether the phone is on landscape orientation or not */
val isOnLandscapeOrientation: Boolean
    get() = appOrientation == Configuration.ORIENTATION_LANDSCAPE


/**
 * Create a [ColorStateList] from
 * a color resource
 */
fun colorStateListFromRes(@ColorRes colorRes: Int): ColorStateList {
    return ColorStateList(appColor(colorRes))
}

/**
 * Create a [ColorStateList] from
 * a color int
 */
@Suppress("FunctionName")
fun ColorStateList(@ColorInt color: Int): ColorStateList {
    return ColorStateList.valueOf(color)
}

/**
 * Build a spanned from multiple parts
 */
fun buildSpanned(vararg parts: CharSequence): Spanned {
    return org.jetbrains.anko.buildSpanned {
        append(*parts)
    }
}

/**
 * Log the given value using the logging
 * tag LOGEXECUTION and return the parameter
 */
fun <T> logExecution(value: T): T {
    Timber.tag("LOGEXECUTION").i("$value")
    return value
}