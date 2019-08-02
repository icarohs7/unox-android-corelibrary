package base.corelibrary.domain.extensions

import android.graphics.Point
import android.view.Window
import android.view.WindowManager
import arrow.core.Tuple2
import kotlin.math.roundToInt

/**
 * Resize the given [Window] to the given percentage
 * of the screen
 */
fun Window.resizeToScreenPercent(widthPercent: Int, heightPercent: Int) {
    val point = Point()
    val display = windowManager.defaultDisplay
    display.getSize(point)
    val width = point.x * (widthPercent / 100.0)
    val height = point.y * (heightPercent / 100.0)
    setLayout(width.roundToInt(), height.roundToInt())
}

/**
 * Show the soft input keyboard
 * on the given [Window]
 */
fun Window.showKeyboard() {
    clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
}

/**
 * Return the width and height of the
 * given window
 */
fun Window.widthAndHeight(): Tuple2<Int, Int> {
    val manager = windowManager
    val point = Point()
    manager.defaultDisplay.getSize(point)
    return Tuple2(point.x, point.y)
}