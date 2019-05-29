package base.corelibrary.domain.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.toPublisher
import io.reactivex.Flowable

fun <T> LiveData<T>.toFlowable(lifecycle: LifecycleOwner): Flowable<T> {
    return Flowable.fromPublisher(this.toPublisher(lifecycle))
}