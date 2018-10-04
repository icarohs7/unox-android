package com.github.icarohs7.notification

import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class NotificationModuleKtTest {
    @Test
    fun `should emit notification string messages`() {
        val provider = simpleMessagingProvider()
        provider.channel.observeForever { it shouldEqual "Hello, World!" }
        provider.notify("Hello, World!")
    }

    @Test
    fun `should fail on unexpected message`() {
        val provider = simpleMessagingProvider()
        provider.channel.observeForever { it shouldEqual "Different" }
        provider.notify("Hello, World!")
    }

    @Test
    fun `should emit notification objects`() {
        val provider = objectNotificationProvider<Int>()
        provider.channel.observeForever { it shouldEqual 1532 }
        provider.notify(1532)
    }

    @Test
    fun `should fail on unexpected object`() {
        val provider = objectNotificationProvider<Int>()
        provider.channel.observeForever { it shouldEqual 32 }
        provider.notify(1532)
    }
}