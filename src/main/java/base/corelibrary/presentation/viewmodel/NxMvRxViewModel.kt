package base.corelibrary.presentation.viewmodel

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.RealMvRxStateStore
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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.plus

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
     * Connect the given [Flow] to the
     * state of the viewmodel, updating
     * the state on each emission
     */
    fun Flow<S>.connectToState(): Job {
        return connectToState { this }
    }

    /**
     * Use the given transformer and for each
     * emission of the given [Flow], transform
     * the emmited item and apply the reducer to
     * evolve the state
     */
    fun <T> Flow<T>.connectToState(transformer: S.(T) -> S): Job {
        return onEach { item -> setState { transformer(this, item) } }
                .launchIn(this@NxMvRxViewModel + Dispatchers.Default)
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
     * Launch the collection of the given Flow
     * on the coroutine scope of this component
     */
    fun Flow<*>.launchInScope(): Job = launchIn(this@NxMvRxViewModel)

    override fun onCleared() {
        cancelCoroutineScope()
        super.onCleared()
    }
}