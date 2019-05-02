package base.corelibrary.domain.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.github.icarohs7.unoxandroidarch.extensions.asBrDate
import com.github.icarohs7.unoxandroidarch.extensions.asDate
import com.github.icarohs7.unoxandroidarch.extensions.asShortTime
import com.github.icarohs7.unoxcore.extensions.asCurrency
import java.util.Date

@BindingAdapter("app:numberText")
fun <T : Number> TextView.setNumberText(value: T?) {
    text = value?.toString()
}

@BindingAdapter("app:currencyText")
fun TextView.setCurrencyText(value: Double) {
    text = value.asCurrency()
}

@BindingAdapter("app:brDateText")
fun TextView.setBrDate(value: Date?) {
    text = value?.asBrDate
}

@BindingAdapter("app:brStringDateText")
fun TextView.setBrDate(value: String?) {
    text = value?.asDate()?.asBrDate
}

@BindingAdapter("app:shortTimeText")
fun TextView.setShortTime(value: Date?) {
    text = value?.asShortTime
}

@BindingAdapter("app:shortStringTimeText")
fun TextView.setShortTime(value: String?) {
    text = value?.asDate()?.asShortTime
}