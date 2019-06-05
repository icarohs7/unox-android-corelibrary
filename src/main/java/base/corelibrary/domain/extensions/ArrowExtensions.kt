package base.corelibrary.domain.extensions

import arrow.core.Try
import arrow.core.Failure
import arrow.core.Success
import com.github.icarohs7.unoxcore.extensions.orThrow

/**
 * Unwrap the given Try instance, throwing
 * an exception if it's a [Failure] or returning
 * the value if it's a [Success]
 */
operator fun <T> Try<T>.not(): T {
    return this.orThrow()
}