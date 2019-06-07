package base.corelibrary.domain.extensions

import arrow.core.Failure
import arrow.core.Success
import arrow.core.Try
import com.github.icarohs7.unoxcore.extensions.orThrow

/**
 * Intermediary operator used to enable double
 * bang on try instances
 */
operator fun <T> Try<T>.not(): TryWrapper<T> {
    return TryWrapper(this)
}

/**
 * Unwrap the given TryWrapper instance, throwing
 * an exception if it's a [Failure] or returning
 * the value if it's a [Success]
 */
operator fun <T> TryWrapper<T>.not(): T {
    return this.t.orThrow()
}

class TryWrapper<T>(val t: Try<T>)