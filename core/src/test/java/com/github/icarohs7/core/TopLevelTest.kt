package com.github.icarohs7.core

import com.github.icarohs7.core.extensions.hasTheSameDispatcherAs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import se.lovef.assert.v1.shouldBeTrue
import se.lovef.assert.v1.shouldEqual

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class TopLevelTest {

    @Test
    fun `should ignore return values`() {
        noReturn { 5 } shouldEqual Unit
        noReturn { } shouldEqual Unit
        noReturn { "Hello" + " World!" } shouldEqual Unit

        runBlocking {
            noReturnSusp { async { 5 }.await() } shouldEqual Unit
            noReturnSusp { async { false }.await() } shouldEqual Unit
            noReturnSusp { async { "Hello" }.await() } shouldEqual Unit
        }
    }

    @Test
    fun mutableLiveDataOf() {
        val ld1 = mutableLiveDataOf("HI!")
        ld1.value shouldEqual "HI!"

        val ld2 = mutableLiveDataOf(1532)
        ld2.value shouldEqual 1532

        val ld3 = mutableLiveDataOf { s: String -> s.toUpperCase() }
        ld3.value?.invoke("omg") shouldEqual "OMG"
    }

    @Test
    fun `should run operations on background`(): Unit = runBlocking {
        withContext(Dispatchers.Default) {
            (coroutineContext hasTheSameDispatcherAs Dispatchers.Default).shouldBeTrue()

            onBackground(Dispatchers.IO, Dispatchers.Default) {
                (coroutineContext hasTheSameDispatcherAs Dispatchers.IO).shouldBeTrue()
            }
            Unit
        }

        withContext(Dispatchers.IO) {
            (coroutineContext hasTheSameDispatcherAs Dispatchers.IO).shouldBeTrue()

            onBackground(Dispatchers.Default, Dispatchers.IO) {
                (coroutineContext hasTheSameDispatcherAs Dispatchers.Default).shouldBeTrue()
            }
            Unit
        }

        Unit
    }
}