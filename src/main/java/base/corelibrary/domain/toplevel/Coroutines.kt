package base.corelibrary.domain.toplevel

import com.github.icarohs7.unoxandroidarch.toplevel.whileLoading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun CoroutineScope.launchLoading(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
) {
    launch(context, start) {
        whileLoading(fn = block)
    }
}

/**
 * Create a flow that emit once every [emissionDelayMillis]
 */
fun flowTimer(emissionDelayMillis: Long = 1000L): Flow<Unit> = flow {
    while (true) {
        emit(Unit)
        delay(emissionDelayMillis)
    }
}