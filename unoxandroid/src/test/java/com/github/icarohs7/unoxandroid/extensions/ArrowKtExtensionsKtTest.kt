/*
 * MIT License
 *
 * Copyright (c) 2018 Icaro R D Temponi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.icarohs7.unoxandroid.extensions

import arrow.core.Failure
import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.core.Success
import arrow.core.Try
import arrow.core.getOrElse
import arrow.core.some
import arrow.core.success
import arrow.core.toOption
import org.junit.Test
import se.lovef.assert.throws
import se.lovef.assert.typeIs
import se.lovef.assert.v1.shouldBeTrue
import se.lovef.assert.v1.shouldEqual

class ArrowKtExtensionsKtTest {

    @Test
    fun `convert nullable value to Try`() {
        //Given
        val v1: String? = null
        val v2: String? = "Omai wa mou shindeiru"
        val v3: Int? = null
        val v4 = 1532

        //When
        val t1 = v1.toTry()
        val t2 = v2.toTry()
        val t3 = v3.toTry()
        val t4 = v4.toTry()

        //Then
        t1.isFailure().shouldBeTrue()
        ;{ throw t1.failed().getOrElse { Exception() } } throws KotlinNullPointerException::class
        t2.getOrElse { "" } shouldEqual "Omai wa mou shindeiru"
        t3.isFailure().shouldBeTrue()
        ;{ throw t3.failed().getOrElse { Exception() } } throws KotlinNullPointerException::class
        t4.getOrElse { -42 } shouldEqual 1532
    }

    @Test
    fun `should map option removing treating null returns`() {
        //Given
        val o1 = Some(1)
        val o2 = Some(2)
        val o3 = Some(3)
        //When
        val r1 = o1.nullMap { it + 10 }.nullMap { it * 10 }
        val a1: Int? = null
        val r2 = o2.nullMap { a1 }.nullMap { it * 10 }
        val r3 = o3.nullMap { it * 2 }.nullMap { it * 5 }
        //Then
        r1 shouldEqual Some(110)
        r2 shouldEqual None
        r3 shouldEqual Some(30)
    }

    @Test
    fun `should map value to option removing nullability`() {
        //Given
        val v1: Int? = 10
        val v2: Int? = null
        val v3: Int? = 1532
        //When
        val a: Int? = null
        val r1 = v1.optionMap { a }
        val r2 = v2.optionMap { it * 2 }
        val r3 = v3.optionMap { it * 2 }
        //Then
        r1 shouldEqual None
        r2 shouldEqual None
        r3 shouldEqual Some(3064)
    }

    @Test
    fun `first of wrapped list`() {
        val l1 = listOf(1, 2, 3).success()
        val l2 = listOf(1, 2, 3).some()
        val l3 = emptyList<Int>().success()
        val l4 = emptyList<Int>().some()

        l1.first() shouldEqual Try.just(1)
        l2.first() shouldEqual Option.just(1)
        val fn = fun() { throw l3.first().failed().getOrElse { Exception() } }
        fn throws NoSuchElementException::class
        l4.first() shouldEqual None
    }

    @Test
    fun `try string or empty value`() {
        //Given
        val t1 = Try { "Omai wa mou shindeiru!" }
        val t2 = Try { throw Exception("wat") }
        val t3 = Try { "NANI!?" }
        //When
        val r1 = t1.orEmpty()
        val r2 = t2.orEmpty()
        val r3 = t3.orEmpty()
        //Then
        r1 shouldEqual "Omai wa mou shindeiru!"
        r2 shouldEqual ""
        r3 shouldEqual "NANI!?"
    }

    @Test
    fun `should map cathing exceptions thrown`() {
        //Given
        val t1 = Try { 1 }
        val t2 = Try { "hi" }
        //When
        val r1 = t1.mapCatching { 15 }
        val r2 = t2.mapCatching { it.toInt() }
        //Then
        r1 shouldEqual Success(15)
        r2 typeIs Failure::class
    }

    @Test
    fun `get option string or empty`() {
        //Given
        val o1: String? = "Omai wa mou shindeiru!"
        val o2: String? = null
        val o3: String? = "NANI!?"
        //When
        val r1 = o1.toOption().orEmpty()
        val r2 = o2.toOption().orEmpty()
        val r3 = o3.toOption().orEmpty()
        //Then
        r1 shouldEqual "Omai wa mou shindeiru!"
        r2 shouldEqual ""
        r3 shouldEqual "NANI!?"
    }

    @Test
    fun `get option list or empty`() {
        //Given
        val l1 = Some(listOf(1, 2, 3))
        val l2 = Option.empty<List<Int>>()
        val l3 = listOf("Omai wa mou shindeiru!", "NANI!?").toOption()
        //When
        val r1 = l1.orEmpty()
        val r2 = l2.orEmpty()
        val r3 = l3.orEmpty()
        //Then
        r1 shouldEqual listOf(1, 2, 3)
        r2 shouldEqual emptyList()
        r3 shouldEqual listOf("Omai wa mou shindeiru!", "NANI!?")
    }

    @Test
    fun `try or throw`() {
        //Given
        val t1 = Try { 1532 }
        val t2 = Try { "Omai wa mou shindeiru" }
        val t3 = Try { "NANI!?" }
        val t4 = Try { throw Exception() }
        val t5 = Try { throw UnsupportedOperationException("wat") }
        //Then
        t1.orThrow() shouldEqual 1532
        t2.orThrow() shouldEqual "Omai wa mou shindeiru"
        t3.orThrow() shouldEqual "NANI!?"
        { t4.orThrow() } throws Exception::class
        { t5.orThrow() } throws UnsupportedOperationException::class
    }

    @Test
    fun `should remove nullability from option`() {
        //Given
        val o1 = Some<Int?>(10)
        val o2 = Some<Int?>(null)
        val o3 = Some<String?>("Hey")
        //When
        val r1 = o1.removeNull()
        val r2 = o2.removeNull()
        val r3 = o3.removeNull()
        //Then
        r1 shouldEqual Some(10)
        r2 shouldEqual None
        r3 shouldEqual Some("Hey")
    }

    @Test
    fun `should map not null values`() {
        //Given
        val o1 = Some<Int?>(10)
        val o2 = Some<Int?>(null)
        val o3 = Some<String?>("Hey")
        //When
        val r1 = o1.mapNotNull { it * 10 }
        val r2 = o2.mapNotNull { it + 2 }
        val r3 = o3.mapNotNull { "$it you, out there in the cold" }
        //Then
        r1 shouldEqual Some(100)
        r2 shouldEqual None
        r3 shouldEqual Some("Hey you, out there in the cold")
    }

    @Test
    fun `should return the list inside a try or empty list`() {
        //Given
        val l1 = Try { listOf(1, 2, 3) }
        val l2 = Try { listOf(4, 5, 6) }
        val l3 = Try { listOf(1, 2, 3 / 0) }

        //When
        val ex1 = l1.orEmpty()
        val ex2 = l2.orEmpty()
        val ex3 = l3.orEmpty()

        //Then
        ex1 shouldEqual listOf(1, 2, 3)
        ex2 shouldEqual listOf(4, 5, 6)
        ex3 shouldEqual emptyList()
    }

    @Test
    fun `nullMap test`() {
        //Given
        val o1 = 5.optionMap { 1532 }
        val o2 = (null as? Int?).optionMap { 10 }
        val o3 = 1532.optionMap { null }

        //When
        val e1 = o1.orNull()
        val e2 = o2.orNull()
        val e3 = o3.orNull()

        //Then
        e1 shouldEqual 1532
        e2 shouldEqual null
        e3 shouldEqual null
    }

    @Test
    fun `optionMap test`() {
        //Given
        val v1: Int? = 10
        val v2: Int? = null
        val v3: String? = "omai wa mou shindeiru"
        val v4: String? = null

        //When
        val e1 = v1.optionMap { it * 2 }
        val e2 = v2.optionMap { it * 3 }
        val e3 = v3.optionMap { it }
        val e4 = v4.optionMap { "$it will be null" }

        //Then
        e1 shouldEqual Some(20)
        e1 typeIs Option.just(0)::class
        e2 shouldEqual None
        e3 shouldEqual Some("omai wa mou shindeiru")
        e3 typeIs Option.just("")::class
        e4 shouldEqual None
    }
}