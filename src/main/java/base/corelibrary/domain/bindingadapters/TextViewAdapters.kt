package base.corelibrary.domain.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.github.icarohs7.unoxandroidarch.extensions.asBrDate
import com.github.icarohs7.unoxandroidarch.extensions.asDate
import com.github.icarohs7.unoxandroidarch.extensions.asShortTime
import com.github.icarohs7.unoxcore.extensions.asCurrency
import java.util.Date

@BindingAdapter("numberText")
fun TextView.setNumberText(value: Number) {
    text = "$value"
}

@BindingAdapter("currencyText")
fun TextView.setCurrencyText(value: Double) {
    text = value.asCurrency()
}

@BindingAdapter("brDateText")
fun TextView.setBrDate(value: Date) {
    text = value.asBrDate
}

@BindingAdapter("brStringDateText")
fun TextView.setBrDate(value: String) {
    text = value.asDate().asBrDate
}

@BindingAdapter("shortTimeText")
fun TextView.setShortTime(value: Date) {
    text = value.asShortTime
}

@BindingAdapter("shortStringTimeText")
fun TextView.setShortTime(value: String) {
    text = value.asDate().asShortTime
}