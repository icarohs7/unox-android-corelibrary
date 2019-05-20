package base.corelibrary.domain.extensions

import arrow.core.orNull
import arrow.effects.IO
import com.github.icarohs7.unoxcore.extensions.tryIO

/**
 * Whether the given IO had success executing or not,
 * where a success is one of:
 * * The execution doesn't throw an exception and the return
 * type is not Boolean
 * * The execution doesn't throw an exception, the result is
 * a Boolean and it's true
 */
val IO<*>.syncIsSuccess: Boolean
    get() {
        val result = tryIO().orNull() ?: return false
        return if (result is Boolean) result else true
    }

class IOWrapper<T> internal constructor(val io: IO<T>)

/**
 * Wrap the given IO into a wrapper, used as a
 * workaround to allow using the double ! operator
 */
operator fun <T> IO<T>.not(): IOWrapper<T> {
    return IOWrapper(this)
}

/**
 * Execute and unwrap the given IO, blocking the
 * current thread until the operation is done
 */
operator fun <T> IOWrapper<T>.not(): T {
    return io.unsafeRunSync()
}