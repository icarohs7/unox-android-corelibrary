package base.corelibrary.domain.extensions

import android.widget.AutoCompleteTextView

val AutoCompleteTextView.items
    get() = (0 until adapter.count).map { index -> adapter.getItem(index) }

var AutoCompleteTextView.selectedItemIndex: Int
    get() = items.indexOfFirst { it?.toString() == text?.toString() }
    set(value) {
        setText(items.getOrNull(value)?.toString())
    }