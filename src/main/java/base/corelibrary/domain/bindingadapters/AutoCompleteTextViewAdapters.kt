package base.corelibrary.domain.bindingadapters

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import base.corelibrary.domain.extensions.selectedItemIndex

@BindingAdapter("app:entries", "app:entryLayout", "app:includeEmpty", requireAll = false)
fun AutoCompleteTextView.setItems(
        items: Array<String>?,
        @LayoutRes layout: Int? = null,
        includeEmpty: Boolean? = null
) {
    setAdapter(
            ArrayAdapter(
                    context,
                    layout ?: android.R.layout.simple_spinner_dropdown_item,
                    (if (includeEmpty == true) arrayOf("") else emptyArray()) + items.orEmpty()
            )
    )

    if (text?.toString().isNullOrBlank() && items?.isNotEmpty() == true)
        selectedItemIndex = 0
}

@BindingAdapter("app:onItemSelected")
fun AutoCompleteTextView.onItemSelected(listener: AdapterView.OnItemSelectedListener?) {
    onItemSelectedListener = listener
}