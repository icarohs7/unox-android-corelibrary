package base.corelibrary.domain.extensions

import android.widget.TextView
import androidx.core.view.isGone

/**
 * Set the text of the text view if the given
 * string is not null and not blank, otherwise
 * set the TextView visilibity to Gone
 */
fun TextView.setTextOrBeGone(text: String?) {
    when {
        text == null -> isGone = true
        text.isBlank() -> isGone = true
        else -> this.text = text
    }
}