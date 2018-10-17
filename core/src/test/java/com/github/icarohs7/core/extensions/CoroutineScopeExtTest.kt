package com.github.icarohs7.core.extensions

import com.github.icarohs7.core.toplevel.NXBGPOOL
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import se.lovef.assert.v1.shouldBeCloseTo
import se.lovef.assert.v1.shouldEqual
import kotlin.system.measureTimeMillis

class CoroutineScopeExtTest {
    @Test
    fun `should wait for a list of jobs`() {
        val scope = CoroutineScope(NXBGPOOL)
        var testV = 0
        val d = measureTimeMillis {
            runBlocking {
                scope.awaitJobs {
                    this += launch { delay(1000) }
                    this += launch { delay(1200) }
                    this += launch { delay(800) }
                    this += launch { testV = 1532 }
                }
            }
        }

        d shouldBeCloseTo 1200 tolerance 400
        testV shouldEqual 1532
    }
}