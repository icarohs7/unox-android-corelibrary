package base.corelibrary.domain.extensions

import android.view.View
import android.widget.ViewAnimator
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.github.icarohs7.unoxandroidarch.extensions.doOnEnd

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

fun View.rootParent(): View? {
    var p = (parent as? View?)
    while (p?.parent as? View? != null)
        p = p.parent as? View?
    return p
}