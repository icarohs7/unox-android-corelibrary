package base.corelibrary.domain.extensions

import android.widget.EditText

val EditText.textOrEmpty: CharSequence
    get() = text?.toString().orEmpty()