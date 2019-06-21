package base.corelibrary.domain.extensions

import android.graphics.Point
import android.util.Log
import android.view.Window
import arrow.core.toT
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