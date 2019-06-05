package base.corelibrary.presentation.viewmodel

import arrow.core.Try
import arrow.core.orNull
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.RealMvRxStateStore
import com.airbnb.mvrx.Success
import com.github.icarohs7.unoxandroidarch.UnoxAndroidArch
import com.github.icarohs7.unoxcore.extensions.coroutines.cancelCoroutineScope
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async

/**
 * Base ViewModel implementing a [CoroutineScope]
 * and inheriting from [BaseMvRxViewModel]
 */
open class NxMvRxViewModel<S : MvRxState>(initialState: S) : BaseMvRxViewModel<S>(
        initialState,
        UnoxAndroidArch.isDebug,
        RealMvRxStateStore(initialState)
), CoroutineScope by MainScope() {
    /**
     * Connect the given flowable to the
     * state of the viewmodel, updating
     * the state on each emission
     */
    fun Flowable<S>.connectToState(): Disposable {
        return connectToState { this }
    }

    /**
     * Use the given transformer and for each
     * emission of the given Flowable, transform
     * the emmited item and apply the reducer to
     * evolve the state
     */
    fun <T> Flowable<T>.connectToState(transformer: S.(T) -> S): Disposable {
        return subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { item -> setState { transformer(this, item) } }
    }

    /**
     * Helper to map a suspend function to an Async property on the state object.
     */
    fun <T : Any> CoroutineScope.executeAsync(
            block: suspend () -> T,
            dispatcher: CoroutineDispatcher = Dispatchers.Default,
            reducer: S.(Async<T>) -> S
    ): Deferred<T?> {
        return async(this.coroutineContext + dispatcher) {
            execute(block, reducer)
        }
    }

    /**
     * Helper to map a suspend function to an Async property on the state object.
     */
    suspend fun <T : Any> execute(
            block: suspend () -> T,
            reducer: S.(Async<T>) -> S
    ): T? {
        setState { reducer(Loading()) }
        val result = Try { block() }

        setState {
            when (result) {
                is Try.Success -> reducer(Success(result.value))
                is Try.Failure -> reducer(Fail(result.exception))
            }
        }

        return result.orNull()
    }

    override fun onCleared() {
        cancelCoroutineScope()
        super.onCleared()
    }
}