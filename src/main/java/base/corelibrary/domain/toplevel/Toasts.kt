package base.corelibrary.domain.toplevel

import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import splitties.init.appCtx
import splitties.resources.appStr

fun toast(msgRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(appStr(msgRes), duration)
}

fun toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    GlobalScope.launch(Dispatchers.Main) { Toast.makeText(appCtx, msg, duration).show() }
}