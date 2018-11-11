package com.github.icarohs7.library.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test
import se.lovef.assert.v1.shouldBeTrue

class CoroutineContextExtTest {

    @Test
    fun hasTheSameDispatcherAs(): Unit = runBlocking {
        withContext(Dispatchers.Default) {
            (coroutineContext hasTheSameDispatcherAs Dispatchers.Default).shouldBeTrue()
        }

        withContext(Dispatchers.IO) {
            (coroutineContext hasTheSameDispatcherAs Dispatchers.IO).shouldBeTrue()
        }

        Unit
    }
}