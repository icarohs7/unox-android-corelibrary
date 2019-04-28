package base.corelibrary.domain.extensions

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.github.florent37.viewanimator.AnimationBuilder
import com.github.florent37.viewanimator.ViewAnimator
import com.github.icarohs7.unoxandroidarch.extensions.doOnEnd
import splitties.systemservices.inputMethodManager

fun View.animatedShow(duration: Long = 200L) {
    isVisible = true
    animate().scaleX(1f).scaleY(1f).setDuration(duration).doOnEnd { isVisible = true }
}

fun View.animatedHide(duration: Long = 200L) {
    animate().scaleX(0f).scaleY(0f).setDuration(duration).doOnEnd { isGone = true }
}

fun View.animatedToggleVisibility(isShown: Boolean, duration: Long = 200L) {
    if (isShown) animatedShow(duration)
    else animatedHide(duration)
}

fun View.animatedChangeAlpha(newAlpha: Float, duration: Long = 200L) {
    animate().alpha(newAlpha).duration = duration
}

fun View.viewAnimate(): AnimationBuilder {
    return ViewAnimator.animate(this)
}