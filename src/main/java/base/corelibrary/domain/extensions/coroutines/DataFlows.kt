@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package base.corelibrary.domain.extensions.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * An infinite flow the will broadcast sent elements
 * to its observers
 */
interface SubjectFlow<T> {
    suspend fun send(value: T)
    fun offer(value: T): Boolean

    fun observe(scope: CoroutineScope, observer: (T) -> Unit): Job
    fun flow(): Flow<T>
}

/**
 * Wrapper class holding an [kotlinx.coroutines.channels.ArrayBroadcastChannel]
 * and a flow emitting its items
 */
class PublishSubjectFlow<T> : SubjectFlow<T> {
    private val channel: BroadcastChannel<T> = BroadcastChannel(1)

    override suspend fun send(value: T): Unit = channel.send(value)
    override fun offer(value: T): Boolean = channel.offer(value)

    override fun observe(scope: CoroutineScope, observer: (T) -> Unit): Job {
        return flow().onEach { observer(it) }.launchIn(scope)
    }

    override fun flow(): Flow<T> = channel.asFlow()
}

/**
 * Wrapper class holding an [kotlinx.coroutines.channels.ConflatedBroadcastChannel]
 * and a flow emitting its items
 */
class BehaviorSubjectFlow<T>(initialValue: T? = null) : SubjectFlow<T> {
    private val channel: ConflatedBroadcastChannel<T> = initialValue
            ?.let(::ConflatedBroadcastChannel)
            ?: ConflatedBroadcastChannel()

    var value: T
        get() = channel.value
        set(value) = channel.offer(value).let { Unit }

    override suspend fun send(value: T) = channel.send(value)
    override fun offer(value: T): Boolean = channel.offer(value)

    override fun observe(scope: CoroutineScope, observer: (T) -> Unit): Job {
        return flow().onEach { observer(it) }.launchIn(scope)
    }

    override fun flow(): Flow<T> = channel.asFlow()
}