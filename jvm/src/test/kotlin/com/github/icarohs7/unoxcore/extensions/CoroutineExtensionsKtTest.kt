package com.github.icarohs7.unoxcore.extensions

import arrow.core.Tuple2
import arrow.core.Tuple3
import arrow.core.Tuple4
import arrow.core.Tuple5
import arrow.core.Tuple6
import com.github.icarohs7.unoxcore.extensions.coroutines.plus
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class CoroutineExtensionsKtTest {
    @Test
    fun should_combine_flows() {
        val f1 = flowOf(1)
        val f2 = flowOf(11)
        val f3 = flowOf(21)
        val f4 = flowOf(31)
        val f5 = flowOf(41)
        val f6 = flowOf(51)

        runBlockingTest {
            val f12 = f1 + f2
            f12.first() shouldEqual Tuple2(1, 11)
        }

        runBlockingTest {
            val f123 = f1 + f2 + f3
            f123.first() shouldEqual Tuple3(1, 11, 21)
        }

        runBlockingTest {
            val f1234 = f1 + f2 + f3 + f4
            f1234.first() shouldEqual Tuple4(1, 11, 21, 31)
        }

        runBlockingTest {
            val f12345 = f1 + f2 + f3 + f4 + f5
            f12345.first() shouldEqual Tuple5(1, 11, 21, 31, 41)
        }

        runBlockingTest {
            val f123456 = f1 + f2 + f3 + f4 + f5 + f6
            f123456.first() shouldEqual Tuple6(1, 11, 21, 31, 41, 51)
        }
    }
}