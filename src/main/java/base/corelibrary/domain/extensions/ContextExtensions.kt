package base.corelibrary.domain.extensions

import android.content.Context
import base.corelibrary.R
import com.github.icarohs7.unoxandroidarch.extensions.backgroundTintColorResource
import com.github.icarohs7.unoxandroidarch.extensions.showConfirmDialog
import splitties.resources.str

fun Context.showSigeConfirmDialog(titleRes: Int = 0, messageRes: Int = 0, yesHandler: () -> Unit) {
    val title = if (titleRes > 0) str(titleRes) else ""
    val message = if (messageRes > 0) str(titleRes) else ""
    showConfirmDialog(title, message) { dialog ->
        btnNoDialogyesno.backgroundTintColorResource = R.color.gray_button_color
        btnYesDialogyesno.backgroundTintColorResource = R.color.primary_button_color
        setYesHandler {
            yesHandler()
            dialog.dismiss()
        }
    }
}