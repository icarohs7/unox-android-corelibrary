//TODO move all to unox-core
package base.corelibrary.domain.extensions

import android.util.Log
import arrow.core.Tuple2
import arrow.core.Tuple3
import arrow.core.Tuple4
import arrow.core.Tuple5
import arrow.core.Tuple6
import com.github.icarohs7.unoxcore.extensions.coroutines.job
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.rx2.rxMaybe
import kotlinx.coroutines.rx2.rxSingle

/**
 * Combine 2 Flows into a Tuple using
 * [combineLatest]
 */
operator fun <A, B> Flow<A>.plus(
        b: Flow<B>
): Flow<Tuple2<A, B>> {
    return combineLatest(b) { t1, t2 ->
        Tuple2(t1, t2)
    }
}

/**
 * Combine 3 Flows into a Tuple using
 * [combineLatest]
 */
@JvmName("plus2")
operator fun <A, B, C> Flow<Tuple2<A, B>>.plus(
        c: Flow<C>
): Flow<Tuple3<A, B, C>> {
    return combineLatest(c) { t1, t2 ->
        Tuple3(t1.a, t1.b, t2)
    }
}

/**
 * Combine 4 Flows into a Tuple using
 * [combineLatest]
 */
@JvmName("plus3")
operator fun <A, B, C, D> Flow<Tuple3<A, B, C>>.plus(
        d: Flow<D>
): Flow<Tuple4<A, B, C, D>> {
    return combineLatest(d) { t1, t2 ->
        Tuple4(t1.a, t1.b, t1.c, t2)
    }
}

/**
 * Combine 5 Flows into a Tuple using
 * [combineLatest]
 */
@JvmName("plus4")
operator fun <A, B, C, D, E> Flow<Tuple4<A, B, C, D>>.plus(
        e: Flow<E>
): Flow<Tuple5<A, B, C, D, E>> {
    return combineLatest(e) { t1, t2 ->
        Tuple5(t1.a, t1.b, t1.c, t1.d, t2)
    }
}

/**
 * Combine 6 Flows into a Tuple using
 * [combineLatest]
 */
@JvmName("plus5")
operator fun <A, B, C, D, E, F> Flow<Tuple5<A, B, C, D, E>>.plus(
        f: Flow<F>
): Flow<Tuple6<A, B, C, D, E, F>> {
    return combineLatest(f) { t1, t2 ->
        Tuple6(t1.a, t1.b, t1.c, t1.d, t1.e, t2)
    }
}


/**
 * Map the flow to the filteing of
 * the emitted list using the given
 * predicate
 */
fun <T> Flow<Iterable<T>>.innerFilter(predicate: (T) -> Boolean): Flow<List<T>> {
    return this.map { it.filter(predicate) }
}

/**
 * Map the flow to the mapping of
 * the emitted list using the given
 * transformer
 */
fun <T, R> Flow<Iterable<T>>.innerMap(transform: (T) -> R): Flow<List<R>> {
    return this.map { it.map(transform) }
}

/**
 * Add the current Job as a child
 * of the given [parent] scope,
 * cancelling it when the parent
 * completes
 */
fun Job.addTo(parent: CoroutineScope) {
    parent.job.invokeOnCompletion { this.cancel() }
}