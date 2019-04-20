package base.corelibrary.domain.extensions

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.setupThreads(): Flowable<T> {
    return this.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
}