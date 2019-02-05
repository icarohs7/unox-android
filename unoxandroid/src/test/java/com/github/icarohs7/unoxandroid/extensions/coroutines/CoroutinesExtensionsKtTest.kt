package com.github.icarohs7.unoxandroid.extensions.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test
import se.lovef.assert.v1.shouldBe
import se.lovef.assert.v1.shouldBeCloseTo
import se.lovef.assert.v1.shouldBeFalse
import se.lovef.assert.v1.shouldBeTrue
import se.lovef.assert.v1.shouldEqual
import kotlin.system.measureTimeMillis

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
            coroutineContext.dispatcher shouldEqual Dispatchers.Default
        }

        withContext(Dispatchers.IO) {
            coroutineContext.dispatcher shouldEqual Dispatchers.IO
        }

        Unit
    }

    @Test
    fun `should get dispatcher of scope`() {
        runBlocking {
            CoroutineScope(Dispatchers.Default).launch {
                this.dispatcher shouldEqual Dispatchers.Default
                withContext(Dispatchers.IO) {
                    this.dispatcher shouldEqual Dispatchers.IO
                }
            }
        }
    }

    @Test
    fun `should get dispatcher of context`() {
        runBlocking(Dispatchers.Default) {
            coroutineContext.dispatcher shouldEqual Dispatchers.Default
            withContext(Dispatchers.IO) {
                coroutineContext.dispatcher shouldEqual Dispatchers.IO
            }
        }
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

    @Test
    fun `should create parallel pair`() {
        runBlocking {
            val time = measureTimeMillis { onBackground { parallelPair({ delay(1000) }, { delay(1000) }) } }
            time shouldBeCloseTo 1499 tolerance 500

            val numbers = parallelPair({ 10 }, { 20 })
            val (first, second) = numbers
            first shouldEqual 10
            second shouldEqual 20

            val time2 = measureTimeMillis { parallelPair({ delay(1500) }, { delay(1500) }) }
            time2 shouldBeCloseTo 2249 tolerance 750
        }
    }

    @Test
    fun `should create parallel triple`() {
        runBlocking {
            val time = measureTimeMillis {
                onBackground {
                    parallelTriple({ delay(1000) }, { delay(1000) }, { delay(1000) })
                }
            }
            time shouldBeCloseTo 1499 tolerance 500

            val numbers = parallelTriple({ 10 }, { 20 }, { 30 })
            val (first, second, third) = numbers
            first shouldEqual 10
            second shouldEqual 20
            third shouldEqual 30

            val time2 = measureTimeMillis { parallelTriple({ delay(1500) }, { delay(1500) }, { delay(1500) }) }
            time2 shouldBeCloseTo 2249 tolerance 750
        }
    }

    @Test
    fun `should map collections in parallel`() {
        runBlocking {
            val c1 = listOf(1, 2, 3)
            val r1 = c1.parallelMap { it * 10 }
            r1 shouldEqual listOf(10, 20, 30)

            val c2 = listOf('A', 'B', 'C')
            val time = measureTimeMillis {
                val r2 = c2.parallelMap {
                    delay(1000)
                    it + 1
                }
                r2 shouldEqual listOf('B', 'C', 'D')
            }
            time shouldBeCloseTo 1499 tolerance 500
        }
    }

    @Test
    fun `should filter a collection in parallel`() {
        runBlocking {
            val c1 = (1..1_000_000)
            val t = measureTimeMillis {
                onBackground {
                    val r1 = c1.parallelFilter {
                        delay(500)
                        it <= 10
                    }
                    r1 shouldEqual listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                }
            }
            println("1_000_000 filters time => ${t}ms")
            t shouldBeCloseTo 1500 tolerance 20000

            val c2 = (1..500_000)
            val t2 = measureTimeMillis {
                withContext(Dispatchers.IO) {
                    val r2 = withContext(Dispatchers.IO) {
                        c2.parallelFilter { it % 2 == 0 }
                    }
                    r2 shouldEqual (2..500_000 step 2).toList()
                }
            }
            println("500_000 elements filter time => $t2")

            val c3 = (1..9_000_000)
            val t3 = measureTimeMillis {
                withContext(Dispatchers.IO) {
                    val r3 = withContext(Dispatchers.IO) {
                        c3.parallelFilter { it % 3 == 0 }
                    }
                    r3 shouldEqual (3..9_000_000 step 3).toList()
                }
            }
            println("9_000_000 elements filter time => $t3")
        }
    }
}