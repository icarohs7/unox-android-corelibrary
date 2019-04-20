package base.corelibrary.domain.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.rx2.awaitFirst
import splitties.init.appCtx

class LambdaBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        runBlocking {
            val action = actionsRelay.awaitFirst()
            action(this@LambdaBroadcastReceiver, context)
        }
    }

    companion object {
        private val actionsRelay: Relay<(LambdaBroadcastReceiver, Context?) -> Unit> = BehaviorRelay.create()
        private const val BROADCAST_ACTION: String = "LambdaBroadcastReceiver"

        fun broadcast(lambda: (LambdaBroadcastReceiver, Context?) -> Unit): Unit = with(appCtx) {
            actionsRelay.accept(lambda)
            sendBroadcast(Intent(this, LambdaBroadcastReceiver::class.java))
        }
    }
}