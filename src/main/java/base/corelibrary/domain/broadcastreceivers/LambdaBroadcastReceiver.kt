package base.corelibrary.domain.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import splitties.init.appCtx

class LambdaBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        runBlocking {
            val action = actionsStream.first()
            action(this@LambdaBroadcastReceiver, context)
        }
    }

    companion object {
        private val actionsChannel = ConflatedBroadcastChannel<(LambdaBroadcastReceiver, Context?) -> Unit>()
        private val actionsStream get() = actionsChannel.asFlow()
        private const val BROADCAST_ACTION: String = "LambdaBroadcastReceiver"

        fun broadcast(lambda: (LambdaBroadcastReceiver, Context?) -> Unit): Unit = with(appCtx) {
            actionsChannel.offer(lambda)
            sendBroadcast(Intent(this, LambdaBroadcastReceiver::class.java))
        }
    }
}