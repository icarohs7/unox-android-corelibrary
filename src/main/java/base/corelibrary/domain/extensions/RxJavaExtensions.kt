package base.corelibrary.domain.extensions

import io.reactivex.Flowable

operator fun <T> Flowable<T>.times(other: Flowable<T>): Flowable<T> {
    return this.mergeWith(other)
}