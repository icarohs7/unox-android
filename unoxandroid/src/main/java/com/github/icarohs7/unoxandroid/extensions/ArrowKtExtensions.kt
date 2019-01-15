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
import arrow.core.Option
import arrow.core.Success
import arrow.core.Try
import arrow.core.getOrElse
import arrow.core.toOption

/** Convert a nullable item to a try of it, or a null pointer failure */
fun <T> T?.toTry(): Try<T> =
        Try { this@toTry!! }

/** @return Typeclass safely mapped to the first element of the list */
fun <T> Try<List<T>>.first(): Try<T> =
        this.mapCatching { it.first() }

/** @return Typeclass safely mapped to the first element of the list */
fun <T> Option<List<T>>.first(): Option<T> =
        this.nullMap { it.firstOrNull() }

/** @return Wrapped string or empty */
fun Try<String>.orEmpty(): String =
        this.getOrElse { "" }

/** @return Wrapped string or empty */
fun Option<String>.orEmpty(): String =
        this.getOrElse { "" }

/** @return Wrapped list or an empty one */
fun <T> Try<List<T>>.orEmpty(): List<T> =
        this.getOrElse { emptyList() }

/** @return Wrapped list or an empty one */
fun <T> Option<List<T>>.orEmpty(): List<T> =
        this.getOrElse { emptyList() }

/** @return The wrapped content or throw the given exception when it's a failure */
fun <T> Try<T>.orThrow(): T {
    return when (this) {
        is Success -> value
        is Failure -> throw exception
    }
}

/** Map the wrapped value inside the Try, catching exceptions thrown by the lambda */
inline fun <A, B> Try<A>.mapCatching(f: (A) -> B): Try<B> {
    return this.flatMap { Try { f(it) } }
}

/** @return The option without the wrapped value's nullability, converting it to None if null */
inline fun <A, B> Option<A?>.mapNotNull(f: (A) -> B): Option<B> {
    return this.removeNull().map(f)
}

/** @return The option without the wrapped value's nullability, converting it to None if null */
fun <T> Option<T?>.removeNull(): Option<T> {
    return this.flatMap { it.toOption() }
}

/** Map the value, removing the nullability of the returning value */
inline fun <A, B> Option<A>.nullMap(f: (A) -> B?): Option<B> =
        flatMap { f(it).toOption() }

/** Wrap the nullable receiver into an [Option] and apply the [nullMap] operation */
inline fun <A, B> A?.optionMap(f: (A) -> B?): Option<B> =
        this.toOption().nullMap(f)