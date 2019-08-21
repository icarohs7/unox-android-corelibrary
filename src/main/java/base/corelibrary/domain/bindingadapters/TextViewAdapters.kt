package base.corelibrary.domain.bindingadapters

import android.widget.TextView
import androidx.core.view.isGone
import androidx.databinding.BindingAdapter
import com.github.icarohs7.unoxandroidarch.extensions.asBrDate
import com.github.icarohs7.unoxandroidarch.extensions.asDate
import com.github.icarohs7.unoxandroidarch.extensions.asShortTime
import com.github.icarohs7.unoxcore.extensions.asCurrency
import khronos.toString
import java.text.NumberFormat
import java.util.Date

@BindingAdapter("android:textNumber")
fun <T : Number> TextView.setText(value: T?) {
    text = value?.toString() ?: return
}

@BindingAdapter("android:textLocaleNumber")
fun <T : Number> TextView.setTextLocaleNumber(value: T?) {
    text = value?.let(NumberFormat.getInstance()::format) ?: return
}

@BindingAdapter("android:textCurrency")
fun TextView.setTextCurrency(value: Double?) {
    text = value?.asCurrency() ?: return
}

@BindingAdapter("android:textBrDate")
fun TextView.setTextBrDate(value: Date?) {
    text = value?.asBrDate ?: return
}

@BindingAdapter("android:textBrDate")
fun TextView.setTextBrDate(value: String?) {
    text = value?.asDate()?.asBrDate ?: return
}

@BindingAdapter("android:textBrDate")
fun TextView.setTextBrDate(timestamp: Long?) {
    timestamp ?: return
    val time = Date(timestamp)
    text = time.asBrDate
}

@BindingAdapter("android:textBrDateShortTime")
fun TextView.setTextBrDateShortTime(value: Date?) {
    text = value?.toString("dd/MM/yyyy HH:mm")
}

@BindingAdapter("android:textShortTime")
fun TextView.setTextShortTime(value: Date?) {
    text = value?.asShortTime ?: return
}

@BindingAdapter("android:textShortTime")
fun TextView.setTextShortTime(value: String?) {
    text = value?.asDate()?.asShortTime ?: return
}

@BindingAdapter("app:textOrGone")
fun TextView.setTextOrGone(value: String?) {
    text = value
    isGone = value.isNullOrBlank()
}