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

package com.github.icarohs7.library.extensions

import org.junit.Test
import se.lovef.assert.v1.shouldEqual
import kotlin.reflect.full.findAnnotation

class AnyExtTest {

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