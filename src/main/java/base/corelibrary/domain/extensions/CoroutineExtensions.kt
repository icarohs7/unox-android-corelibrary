package base.corelibrary.domain.extensions

import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.rx2.asFlowable

operator fun <T : Any> Flow<T>.times(other: Flowable<T>): Flowable<T> {
    return this.asFlowable() * other
}