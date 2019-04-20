package base.corelibrary.domain.extensions

import android.app.Notification
import android.content.Context
import base.corelibrary.presentation.AppView
import com.github.icarohs7.unoxandroidarch.Messages
import com.github.icarohs7.unoxandroidarch.extensions.pendingIntentToActivity

/**
 * Emit a notification with vibration and sounds
 * that when clicked is closed and takes the user
 * to the SplashActivity of the application
 */
fun Messages.defaultVibratingNotification(
        ctx: Context,
        title: String,
        message: String,
        bigMessage: String? = null,
        identifier: Int = 9
) {
    textNotification(
            context = ctx,
            title = title,
            message = message,
            bigMessage = bigMessage,
            autoClose = true,
            identifier = identifier,
            pendingIntent = ctx.pendingIntentToActivity(AppView.SPLASH)
    ) {
        flags(Notification.DEFAULT_ALL)
    }
}