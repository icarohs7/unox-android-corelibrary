package base.corelibrary.domain.extensions

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.santalu.maskedittext.MaskEditText

/**
 * Bidirectionally bind the given [MaskEditText] to the parameterized
 * [MutableLiveData] using the lifecycle of the
 * desired [LifecycleOwner]
 */
fun MaskEditText.twoWayMaskedBinding(
        owner: LifecycleOwner,
        liveData: MutableLiveData<String>,
        unmaskFn: (edit: MaskEditText, text: String) -> String = { _, s -> s.replace(Regex("[^\\d]"), "") }
) {
    val edit = this

    liveData.observe(owner, Observer { text ->
        val unmasked = unmaskFn(edit, "${edit.text}")
        if (text != unmasked) edit.setText(text)
    })

    edit.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val unmasked = unmaskFn(edit, "$s")
            if (unmasked != liveData.value) liveData.value = unmasked
        }
    })
}