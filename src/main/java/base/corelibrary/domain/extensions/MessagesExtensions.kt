package base.corelibrary.domain.extensions

import android.content.Context
import base.corelibrary.R
import com.github.icarohs7.unoxandroidarch.Messages
import splitties.resources.appStr

/**
 * Execute the given block while a loading progress dialog is shown,
 * being hidden when the execution finishes
 */
suspend fun <T> Messages.onLoadingDialog(context: Context, block: suspend () -> T): T {
    return withinLoadingDialog(context, appStr(R.string.carregando)) { block() }
}