package com.github.icarohs7.visuals.state

import io.reactivex.Observable
import kotlinx.coroutines.channels.ReceiveChannel

/**
 * Interface describing a container managing a
 * instance of a substate of the application
 */
interface StateManager<T> {
    val observable: Observable<T>
    val channel: ReceiveChannel<T>

    suspend fun lastValue(): T

    /**
     * Apply the reducer to the state and
     * and use the new state created as the
     * current state of the application
     */
    fun reduce(reducer: T.() -> T)

    /**
     * Use the latest state without
     * changing it
     */
    fun use(action: T.() -> Unit)

    companion object
}