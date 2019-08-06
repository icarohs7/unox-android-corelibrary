package base.corelibrary.presentation._baseclasses

import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.plus

/**
 * Base ViewModel implementing a [CoroutineScope]
 * and inheriting from [BaseMvRxViewModel]
 */
open class CoreMvRxViewModel<S : MvRxState>(initialState: S) : BaseMvRxViewModel<S>(
        initialState,
        UnoxAndroidArch.isDebug,
        RealMvRxStateStore(initialState)
) {
    /**
     * Connect the given [Flow] to the
     * state of the viewmodel, updating
     * the state on each emission
     */
    fun Flow<S>.connectToState(): Flow<S> {
        return connectToState { this }
    }

    /**
     * Use the given transformer and for each
     * emission of the given [Flow], transform
     * the emmited item and apply the reducer to
     * evolve the state
     */
    fun <T> Flow<T>.connectToState(transformer: S.(T) -> S): Flow<T> {
        return onEach { item -> setState { transformer(this, item) } }
                .flowOn(Dispatchers.Default)
    }

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
     * Execute a flow and wrap its progression with AsyncData reduced to the global state.
     *
     * @param mapper A map converting the flow type to the desired AsyncData type.
     * @param stateReducer A reducer that is applied to the current state and should return the
     *                     new state. Because the state is the receiver and it likely a data
     *                     class, an implementation may look like: `{ copy(response = it) }`.
     */
    fun <T, V> Flow<T>.execute(mapper: (T) -> V, stateReducer: S.(Async<V>) -> S): Job {
        setState { stateReducer(Loading()) }

        return map<T, Async<V>> { value -> Success(mapper(value)) }
                .catch { emit(Fail(it)) }
                .onEach { asyncData -> setState { stateReducer(asyncData) } }
                .launchInScope()
    }

    /**
     * Launch the collection of the given Flow
     * on the coroutine scope of this component
     */
    fun Flow<*>.launchInScope(): Job = launchIn(viewModelScope)
}