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

/**
 * Chain a infix lambda to an operator, returning itself,
 * with side effects, e.g: mutableListOf<Int>() CHAIN { add(2) }
 */
@Suppress("FunctionName")
infix fun <T> T.CHAIN(fn: T.() -> Unit): T = this.also(fn)

/**
 * Chain a infix lambda to an operator, returning the result,
 *  e.g: 5 PIPE { this + 3 } PIPE { this + 1 } - result=9
 */
@Suppress("FunctionName")
infix fun <T, R> T.PIPE(fn: T.() -> R): R = this.let(fn)

/**
 * Function used to chain operations in a idiomatic way, as:
 * doThis() ASWELL doThat() ASWELL doAnotherThing()
 */
@Suppress("FunctionName")
infix fun Any?.ASWELL(other: Any?): Unit = Unit

/**
 * Extension property returning the simple name of the class
 */
val Any?.TAG: String
    get() = this?.let { obj -> obj::class.simpleName } ?: "null"

/**
 * Ignore the value of the caller and return [Unit]
 */
fun Any?.ignoreResult(): Unit = Unit