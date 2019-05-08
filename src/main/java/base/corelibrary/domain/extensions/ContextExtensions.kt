package base.corelibrary.domain.extensions

import android.content.Context
import base.corelibrary.R
import com.github.icarohs7.unoxandroidarch.extensions.showConfirmDialog
import splitties.resources.colorSL
import splitties.resources.str

fun Context.showSigeConfirmDialog(titleRes: Int = 0, messageRes: Int = 0, yesHandler: () -> Unit) {
    val title = if (titleRes > 0) str(titleRes) else ""
    val message = if (messageRes > 0) str(messageRes) else ""
    showConfirmDialog(title, message) { dialog ->
        btnNoDialogyesno.backgroundTintList = colorSL(R.color.gray_button_color)
        btnYesDialogyesno.backgroundTintList = colorSL(R.color.primary_button_color)
        setYesHandler {
            yesHandler()
            dialog.dismiss()
        }
    }
}