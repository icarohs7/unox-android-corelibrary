package base.corelibrary.domain.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Launch a coroutine and suspend until the given
 * [delayMillis] is elapsed, then executing the
 * given [block]
 */
fun CoroutineScope.launchAfterDelay(
        delayMillis: Long,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend () -> Unit
): Job {
    return launch(context, start) {
        delay(delayMillis)
        block()
    }
}