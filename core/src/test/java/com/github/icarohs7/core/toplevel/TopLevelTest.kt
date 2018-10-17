package com.github.icarohs7.core.toplevel

import com.github.icarohs7.core.UnoxAndroidCoreModule
import com.github.icarohs7.core.extensions.ignoreResult
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

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
        }
        delay(240)
        testList shouldEqual mutableListOf(1, 2, 3, 4)
        ignoreResult()
    }
}