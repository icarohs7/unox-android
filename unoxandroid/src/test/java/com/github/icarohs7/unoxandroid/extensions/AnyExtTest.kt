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

import arrow.core.getOrElse
import org.junit.Test
import se.lovef.assert.v1.shouldBeTrue
import se.lovef.assert.v1.shouldEqual
import se.lovef.assert.v1.throws

class AnyExtTest {

    @Test
    fun `convert nullable value to Try`() {
        //Given
        val v1: String? = null
        val v2: String? = "Omai wa mou shindeiru"
        val v3: Int? = null
        val v4: Int = 1532

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
}