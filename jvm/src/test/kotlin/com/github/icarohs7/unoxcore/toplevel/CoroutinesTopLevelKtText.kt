package com.github.icarohs7.unoxcore.toplevel

import arrow.core.Tuple2
import arrow.core.Tuple3
import arrow.core.Tuple4
import arrow.core.Tuple5
import arrow.core.Tuple6
import arrow.core.Tuple7
import arrow.core.Tuple8
import arrow.core.Tuple9
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class CoroutinesTopLevelKtText {
    @Test
    fun should_create_tuples_with_suspend_function(): Unit = runBlockingTest {
        val f2 = combineTupled(flowOf(1), flowOf("2"))
        f2.first() shouldEqual Tuple2(1, "2")

        val f3 = combineTupled(flowOf(1), flowOf("2"), flowOf(3))
        f3.first() shouldEqual Tuple3(1, "2", 3)

        val f4 = combineTupled(flowOf(1), flowOf("2"), flowOf(3), flowOf("4"))
        f4.first() shouldEqual Tuple4(1, "2", 3, "4")

        val f5 = combineTupled(flowOf(1), flowOf("2"), flowOf(3), flowOf("4"), flowOf(5))
        f5.first() shouldEqual Tuple5(1, "2", 3, "4", 5)

        val f6 = combineTupled(flowOf(1), flowOf("2"), flowOf(3), flowOf("4"), flowOf(5), flowOf("6"))
        f6.first() shouldEqual Tuple6(1, "2", 3, "4", 5, "6")

        val f7 = combineTupled(flowOf(1), flowOf("2"), flowOf(3), flowOf("4"), flowOf(5), flowOf("6"), flowOf(7))
        f7.first() shouldEqual Tuple7(1, "2", 3, "4", 5, "6", 7)

        val f8 = combineTupled(flowOf(1), flowOf("2"), flowOf(3), flowOf("4"), flowOf(5), flowOf("6"), flowOf(7),
                               flowOf("8"))
        f8.first() shouldEqual Tuple8(1, "2", 3, "4", 5, "6", 7, "8")

        val f9 = combineTupled(flowOf(1), flowOf("2"), flowOf(3), flowOf("4"), flowOf(5), flowOf("6"), flowOf(7),
                               flowOf("8"), flowOf(9))
        f9.first() shouldEqual Tuple9(1, "2", 3, "4", 5, "6", 7, "8", 9)
    }
}