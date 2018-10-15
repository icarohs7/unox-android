package com.github.icarohs7.core.extensions

import com.github.icarohs7.core.toplevel.NXBGPOOL
import com.github.icarohs7.core.toplevel.onBgResult
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class DeferredExtTest {
    @Test
    fun `should invoke a callback when a deferred result is returned`() {
        val d1 = onBgResult { delay(800) }
        val t1 = mutableListOf<Int>()
        d1.onResponse(CoroutineScope(NXBGPOOL)) { t1 += 1532 }

        val d2 = onBgResult { delay(400) }
        val t2 = mutableListOf<Int>()
        d2.onResponse(CoroutineScope(NXBGPOOL)) { t2 += 42 }

        runBlocking {
            d1.await() ASWELL d2.await()
            delay(200)
            t1 shouldEqual listOf(1532)
            t2 shouldEqual listOf(42)
            ignoreResult()
        }
    }
}