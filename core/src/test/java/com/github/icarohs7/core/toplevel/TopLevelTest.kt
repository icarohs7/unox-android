package com.github.icarohs7.core.toplevel

import com.github.icarohs7.core.UnoxAndroidCoreModule
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import se.lovef.assert.v1.shouldEqual

@RunWith(RobolectricTestRunner::class)
class TopLevelTest {
    @Test
    fun `should ignore return values`() {
        noReturn { 5 } shouldEqual Unit
        noReturn { } shouldEqual Unit
        noReturn { "Hello" + " World!" } shouldEqual Unit

        runBlocking {
            noReturnSusp { onBgResult { 5 }.await() } shouldEqual Unit
            noReturnSusp { onBgResult { false }.await() } shouldEqual Unit
            noReturnSusp { onBgResult { "Hello" }.await() } shouldEqual Unit
        }
    }

    @Test
    fun `should invoke an action after a delay`(): Unit = runBlocking {
        val testList = mutableListOf(1, 2)
        runAfterDelay(200, UnoxAndroidCoreModule.SCOPE) {
            testList += 3
            testList += 4
        }.join()
        testList shouldEqual mutableListOf(1, 2, 3, 4)
        Unit
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
}