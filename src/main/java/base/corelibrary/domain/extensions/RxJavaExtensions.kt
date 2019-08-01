package base.corelibrary.domain.extensions

import io.reactivex.Flowable
import io.reactivex.Observable
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun <T> Observable<T>.asFlow(): Flow<T> {
    return callbackFlow {
        val onNext: (t: T) -> Unit = { offer(it) }
        val onError: (t: Throwable) -> Unit = { close(it) }
        val onComplete: () -> Unit = { close() }
        val disposable = subscribe(onNext, onError, onComplete)
        awaitClose { disposable.dispose() }
    }
}

fun <T> Flowable<T>.asFlow(): Flow<T> {
    return callbackFlow {
        val onNext: (t: T) -> Unit = { offer(it) }
        val onError: (t: Throwable) -> Unit = { close(it) }
        val onComplete: () -> Unit = { close() }
        val disposable = subscribe(onNext, onError, onComplete)
        awaitClose { disposable.dispose() }
    }
}