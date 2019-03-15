package com.github.icarohs7.unoxandroid.extensions

import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class IterableExtensionsKtTest {
    @Test
    fun `should split a iterable in half`() {
        val l1 = listOf(1, 2, 3, 4)
        l1.partition() shouldEqual Pair(listOf(1, 2), listOf(3, 4))

        val l2 = listOf('A', 'B', 'C')
        l2.partition() shouldEqual Pair(listOf('A', 'B'), listOf('C'))

        val l3 = 1..100
        l3.partition() shouldEqual Pair((1..50).toList(), (51..100).toList())
    }
}