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

package com.github.icarohs7.core.extensions

import org.junit.Test
import se.lovef.assert.v1.shouldEqual
import kotlin.reflect.full.findAnnotation

class AnyExtTest {

    @Test
    fun `should pipe operations`() {
        val p1 = 5 PIPE { this + 3 } PIPE { this + 1 }
        val exp1 = 9

        p1 shouldEqual exp1

        val p2 = "hey" PIPE { "$this you" } PIPE { "$this, out there" } PIPE { "$this in the cold" }
        val exp2 = "hey you, out there in the cold"

        p2 shouldEqual exp2
    }

    @Test
    fun `should chain operations`() {
        val c1 = mutableListOf<Int>() CHAIN { this += 1 } CHAIN { this += 42 }
        val exp1 = mutableListOf(1, 42)

        c1 shouldEqual exp1

        val c2 = mutableListOf<String>() CHAIN
                { this += "hello" } CHAIN
                { add("is there anybody") } CHAIN
                { this += "in there" }
        val exp2 = mutableListOf("hello", "is there anybody", "in there")

        c2 shouldEqual exp2

        var c3 = 0
        val exp3 = 3

        0 ASWELL { c3 += 1 } ASWELL { c3 += 2 } ASWELL { c3 shouldEqual exp3 }
    }

    @Test
    fun `should get the tag of a class`() {
        val tag = this.TAG
        tag shouldEqual "AnyExtTest"
    }

    @Test
    fun `should convert a class to a map`() {
        val firstObj = TestClass3("a", 1, "b")
        val firstMap = firstObj.toMapFromProperties { it.findAnnotation<Label>()?.value ?: "" }
        val firstMapExpected = mapOf(
                "foo" to "a",
                "bar" to "1",
                "hi" to "b"
        )
        firstMap shouldEqual firstMapExpected

        val secondObj = TestClass3("x", 0, "p")
        val secondMap = secondObj.toMapFromProperties { it.findAnnotation<Label>()?.value ?: "" }
        val secondMapExpected = mapOf(
                "foo" to "x",
                "bar" to "0",
                "hi" to "p"
        )
        secondMap shouldEqual secondMapExpected
    }

    data class TestClass3(val foo: String, val bar: Int, @Label("hi") val baz: String)

    @Target(AnnotationTarget.PROPERTY)
    annotation class Label(val value: String)
}