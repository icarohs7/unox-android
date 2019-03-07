package com.github.icarohs7.unoxandroid

import com.github.icarohs7.unoxandroid.delegates.mutableLazy
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface UnoxAndroid {
    /**
     * Companion object storing the settings of the module
     */
    companion object {

        /** Coroutine dispatcher that should be avoided for heavy work */
        var foregroundDispatcher: CoroutineDispatcher by mutableLazy { Dispatchers.Main }
    }
}