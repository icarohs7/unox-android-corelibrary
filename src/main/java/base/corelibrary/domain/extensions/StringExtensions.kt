package base.corelibrary.domain.extensions

import com.github.icarohs7.unoxcore.extensions.valueOr

fun String.toIntOr(fallback: Int): Int {
    return this.toIntOrNull().valueOr(fallback)
}

fun String.toDoubleOr(fallback: Double): Double {
    return this.toDoubleOrNull().valueOr(fallback)
}