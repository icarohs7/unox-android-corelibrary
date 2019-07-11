package base.corelibrary.domain.extensions

import android.text.InputType
import android.widget.EditText

val EditText.textOrEmpty: CharSequence
    get() = text?.toString().orEmpty()

fun EditText.useNumberInputType() {
    inputType = InputType.TYPE_CLASS_NUMBER
}

fun EditText.useDecimalNumberInputType() {
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
}

fun EditText.usePhoneInputType() {
    inputType = InputType.TYPE_CLASS_PHONE
}

fun EditText.useUriInputType() {
    inputType = InputType.TYPE_TEXT_VARIATION_URI
}