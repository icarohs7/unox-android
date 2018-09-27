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

import com.github.icarohs7.core.annotations.Label
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class AnyExtTest : StringSpec() {
    init {
        "should convert a class to a map" {
            val x = TestClass("a", 1, "b")
            val xMap = x.mapOfProperties()
            println(xMap)
            xMap shouldBe mapOf(
                    "foo" to "a",
                    "bar" to "1",
                    "hi" to "b"
            )

            val y = TestClass("x", 0, "p")
            val yMap = y.mapOfProperties()
            yMap shouldBe mapOf(
                    "foo" to "x",
                    "bar" to "0",
                    "hi" to "p"
            )
        }
    }

    data class TestClass(val foo: String, val bar: Int, @Label("hi") val baz: String)
}