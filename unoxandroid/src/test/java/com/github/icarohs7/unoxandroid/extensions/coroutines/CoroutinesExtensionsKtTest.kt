package com.github.icarohs7.unoxandroid.extensions.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

class CoroutinesExtensionsKtTest {
    @Test
    fun `should run operations on background`(): Unit = runBlocking {
        withContext(Dispatchers.Default) {
            coroutineContext.dispatcher shouldEqual Dispatchers.Default

            onBackground(Dispatchers.IO, Dispatchers.Default) {
                coroutineContext.dispatcher shouldEqual Dispatchers.IO
            }
            Unit
        }

        withContext(Dispatchers.Default) {
            coroutineContext.dispatcher shouldEqual Dispatchers.Default

            onBackground(Dispatchers.Default, Dispatchers.IO) {
                coroutineContext.dispatcher shouldEqual Dispatchers.Default
            }
            Unit
        }

        withContext(Dispatchers.IO) {
            coroutineContext.dispatcher shouldEqual Dispatchers.IO

            onBackground(Dispatchers.Default, Dispatchers.IO) {
                coroutineContext.dispatcher shouldEqual Dispatchers.Default
            }
            Unit
        }

        Unit
    }

    @Test
    fun `should run operations on foreground`(): Unit = runBlocking {
        withContext(Dispatchers.Default) {
            coroutineContext.dispatcher shouldEqual Dispatchers.Default

            onForeground(Dispatchers.IO) {
                coroutineContext.dispatcher shouldEqual Dispatchers.IO
            }
            Unit
        }

        withContext(Dispatchers.Default) {
            coroutineContext.dispatcher shouldEqual Dispatchers.Default

            onForeground(Dispatchers.Default) {
                coroutineContext.dispatcher shouldEqual Dispatchers.Default
            }
            Unit
        }

        withContext(Dispatchers.IO) {
            coroutineContext.dispatcher shouldEqual Dispatchers.IO

            onForeground(Dispatchers.Default) {
                coroutineContext.dispatcher shouldEqual Dispatchers.Default
            }
            Unit
        }

        Unit
    }

    @Test
    fun `should create main scope`() {
        //Given
        val scope = MainScope()
        //Then
        scope.dispatcher shouldEqual Dispatchers.Main
    }
}