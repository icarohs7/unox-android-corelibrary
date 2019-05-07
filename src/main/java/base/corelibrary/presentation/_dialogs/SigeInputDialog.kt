package base.corelibrary.presentation._dialogs

import android.content.Context
import base.corelibrary.R
import base.corelibrary.databinding.DialogInputBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.github.icarohs7.unoxandroidarch.extensions.onTextChange
import com.github.icarohs7.unoxandroidarch.presentation.dialogs.BaseMaterialDialog
import splitties.resources.appStr

class SigeInputDialog(ctx: Context) : BaseMaterialDialog<DialogInputBinding>(ctx, R.layout.dialog_input) {
    override suspend fun onCreateBinding() {
        binding.editInput.onTextChange(onChange = { text, _, _, _ ->
            binding.btnOk.isEnabled = !text.isNullOrBlank()
        })
    }

    fun show(
            titleRes: Int = 0,
            hintRes: Int = 0,
            builder: DialogInputBinding.(MaterialDialog) -> Unit
    ): Unit = with(binding) {
        title = if (titleRes > 0) appStr(titleRes) else ""
        hint = if (hintRes > 0) appStr(hintRes) else ""
        builder(dialog)
        show()
    }
}