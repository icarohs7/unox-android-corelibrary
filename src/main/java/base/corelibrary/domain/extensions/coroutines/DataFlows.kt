@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package base.corelibrary.domain.extensions.coroutines

import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

/**
 * Wrapper class holding an [kotlinx.coroutines.channels.ArrayBroadcastChannel]
 * and a flow emitting its items
 */
class PublishDataFlow<T> {
    val channel: BroadcastChannel<T> = BroadcastChannel(1)

    fun offer(value: T): Boolean = channel.offer(value)
    fun flow(): Flow<T> = channel.asFlow()
}

/**
 * Wrapper class holding an [kotlinx.coroutines.channels.ConflatedBroadcastChannel]
 * and a flow emitting its items
 */
class BehaviorDataFlow<T>(initialValue: T) {
    val channel: ConflatedBroadcastChannel<T> = ConflatedBroadcastChannel(initialValue)
    val value: T get() = channel.value

    fun offer(value: T): Boolean = channel.offer(value)
    fun flow(): Flow<T> = channel.asFlow()
}