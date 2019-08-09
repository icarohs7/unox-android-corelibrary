package base.corelibrary.domain.toplevel

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Create a flow that emit once every [emissionDelayMillis]
 */
fun flowTimer(emissionDelayMillis: Long = 1000L): Flow<Unit> = flow {
    while (true) {
        emit(Unit)
        delay(emissionDelayMillis)
    }
}