@file:JvmName("TopLevel")

package base.corelibrary.domain

import android.app.Activity
import android.app.Service
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import arrow.core.Try
import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions.io.fx.fx
import arrow.effects.typeclasses.ConcurrentCancellableContinuation
import base.corelibrary.R
import base.corelibrary.presentation.main.BaseMainActivity
import com.andrognito.flashbar.Flashbar
import com.github.icarohs7.unoxandroidarch.Injector
import com.github.icarohs7.unoxandroidarch.Messages
import com.github.icarohs7.unoxandroidarch.toplevel.Intent
import com.github.icarohs7.unoxandroidarch.toplevel.onActivity
import com.github.icarohs7.unoxcore.UnoxCore
import kotlinx.coroutines.Dispatchers
import org.koin.core.get
import splitties.init.appCtx
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
fun navigate(directions: NavDirections, navOptions: NavOptions? = null) {
    val options = navOptions ?: androidx.navigation.navOptions {
        anim {
            enter = R.anim.enter_animation
            exit = R.anim.exit_animation
            popEnter = R.anim.enter_animation
            popExit = R.anim.exit_animation
        }
    }
    onActivity<BaseMainActivity> {
        Try { navController.navigate(directions, options) }
                .fold(Timber.tag("Navigation")::e) { Timber.tag("Navigation").i("$it") }
    }
}

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

/**
 * Navigate to the giving location popping out all backstack
 * destinations in between
 */
fun navigatePopping(@IdRes destination: Int, args: Bundle? = null) {
    val navOptions = NavOptions
            .Builder()
            .setPopUpTo(destination, true)
            .build()
    navigate(destination, args, navOptions)
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

/**
 * Log the given value using the logging
 * tag LOGEXECUTION and return the parameter
 */
fun <T> logExecution(value: T): T {
    Timber.tag("LOGEXECUTION").i("$value")
    return value
}

/**
 * Execute an [fx] on the [Dispatchers.Default]
 */
fun <A> bgFx(block: suspend ConcurrentCancellableContinuation<ForIO, *>.() -> A): IO<A> {
    return fx {
        continueOn(Dispatchers.Default)
        block()
    }
}

/**
 * @see [fx]
 */
fun <A> uFx(block: suspend ConcurrentCancellableContinuation<ForIO, *>.() -> A): IO<A> {
    return fx(block)
}

/**
 * Syntax sugar to continue the execution on a background dispatcher
 */
suspend inline fun <A> ConcurrentCancellableContinuation<ForIO, A>.continueOnBackground() {
    continueOn(UnoxCore.backgroundDispatcher)
}

/**
 * Syntax sugar to continue the execution on a foreground dispatcher
 */
suspend inline fun <A> ConcurrentCancellableContinuation<ForIO, A>.continueOnForeground() {
    continueOn(UnoxCore.foregroundDispatcher)
}

/**
 * Start the given service passing an optional
 * list of parameters to be inserted into the
 * intent's bundle
 */
inline fun <reified T : Service> startService(vararg bundleExtras: Pair<String, Any?>) {
    val intent = Intent<T>(appCtx)
    intent.putExtras(bundleOf(*bundleExtras))
    appCtx.startService(intent)
}