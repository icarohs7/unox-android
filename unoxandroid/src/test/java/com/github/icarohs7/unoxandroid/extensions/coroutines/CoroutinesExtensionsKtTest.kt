package com.github.icarohs7.unoxandroid.extensions.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test
import se.lovef.assert.v1.shouldBe
import se.lovef.assert.v1.shouldBeFalse
import se.lovef.assert.v1.shouldBeTrue
import se.lovef.assert.v1.shouldEqual

class CoroutinesExtensionsKtTest {
    @Test
    fun `should run operations on background`(): Unit = runBlocking {
        withContext(Dispatchers.Default) {
            (coroutineContext.equalDispatcher(Dispatchers.Default)).shouldBeTrue()

            onBackground(Dispatchers.IO, Dispatchers.Default) {
                (coroutineContext.equalDispatcher(Dispatchers.IO)).shouldBeTrue()
            }
            Unit
        }

        withContext(Dispatchers.Default) {
            (coroutineContext.equalDispatcher(Dispatchers.Default)).shouldBeTrue()

            onBackground(Dispatchers.Default, Dispatchers.IO) {
                (coroutineContext.equalDispatcher(Dispatchers.Default)).shouldBeTrue()
            }
            Unit
        }

        withContext(Dispatchers.IO) {
            (coroutineContext.equalDispatcher(Dispatchers.IO)).shouldBeTrue()

            onBackground(Dispatchers.Default, Dispatchers.IO) {
                (coroutineContext.equalDispatcher(Dispatchers.Default)).shouldBeTrue()
            }
            Unit
        }

        Unit
    }

    @Test
    fun `should run operations on foreground`(): Unit = runBlocking {
        withContext(Dispatchers.Default) {
            (coroutineContext.equalDispatcher(Dispatchers.Default)).shouldBeTrue()

            onForeground(Dispatchers.IO) {
                (coroutineContext.equalDispatcher(Dispatchers.IO)).shouldBeTrue()
            }
            Unit
        }

        withContext(Dispatchers.Default) {
            (coroutineContext.equalDispatcher(Dispatchers.Default)).shouldBeTrue()

            onForeground(Dispatchers.Default) {
                (coroutineContext.equalDispatcher(Dispatchers.Default)).shouldBeTrue()
            }
            Unit
        }

        withContext(Dispatchers.IO) {
            (coroutineContext.equalDispatcher(Dispatchers.IO)).shouldBeTrue()

            onForeground(Dispatchers.Default) {
                (coroutineContext.equalDispatcher(Dispatchers.Default)).shouldBeTrue()
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
        (scope.coroutineContext.equalDispatcher(Dispatchers.Main)).shouldBeTrue()
    }

    @Test
    fun `should cancel coroutine scope`() {
        //Given
        val job = Job()
        val scope = CoroutineScope(job)
        //When
        scope.cancelCoroutineScope()
        //Then
        job.isCancelled.shouldBeTrue()
        job.isCompleted.shouldBeTrue()
        job.isActive.shouldBeFalse()
        scope.job.isCancelled.shouldBeTrue()
        scope.job.isCompleted.shouldBeTrue()
        scope.job.isActive.shouldBeFalse()
    }

    @Test
    fun `should get scope's job`() {
        //Given
        val job = Job()
        val scope = CoroutineScope(job)
        //Then
        scope.job shouldEqual job
        scope.job shouldBe job
    }

    @Test
    fun `should verify if two contexts have the same dispatcher`(): Unit = runBlocking {
        withContext(Dispatchers.Default) {
            (coroutineContext.equalDispatcher(Dispatchers.Default)).shouldBeTrue()
        }

        withContext(Dispatchers.IO) {
            (coroutineContext.equalDispatcher(Dispatchers.IO)).shouldBeTrue()
        }

        Unit
    }

    @Test
    fun `should iterate over the emmited items of a channel`() {
        runBlocking {
            val channel = Channel<Int>()
            val items = mutableListOf<Int>()
            val coroutine = launch(Dispatchers.Default) { channel.forEach { items += it } }
            (1..10).forEach { channel.send(it) }
            channel.close()
            coroutine.join()
            items shouldEqual mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            Unit
        }
    }
}