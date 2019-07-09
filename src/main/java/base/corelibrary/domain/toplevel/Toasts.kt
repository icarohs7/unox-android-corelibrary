package base.corelibrary.domain.toplevel

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import splitties.resources.str

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    GlobalScope.launch(Dispatchers.Main) { Toast.makeText(this@toast, msg, duration).show() }
}

fun Context.toast(msgRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    GlobalScope.launch(Dispatchers.Main) { Toast.makeText(this@toast, str(msgRes), duration).show() }
}

fun Fragment.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    GlobalScope.launch(Dispatchers.Main) { Toast.makeText(requireContext(), msg, duration).show() }
}

fun Fragment.toast(msgRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    GlobalScope.launch(Dispatchers.Main) { Toast.makeText(requireContext(), str(msgRes), duration).show() }
}