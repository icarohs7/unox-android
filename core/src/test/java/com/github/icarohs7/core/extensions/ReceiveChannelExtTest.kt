package com.github.icarohs7.core.extensions

import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class ReceiveChannelExtTest {

    @Test
    fun forEach() {
        val channel = Channel<Int>()
        val items = mutableListOf<Int>()
        val coroutine = GlobalScope.launch { channel.forEach { items += it } }
        runBlocking {
            (1..10).forEach { channel.send(it) }
            channel.close()
            coroutine.join()
            items shouldEqual mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            Unit
        }
    }
}