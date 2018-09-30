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

package com.github.icarohs7.network.extensions

import awaitStringResult
import com.beust.klaxon.Klaxon
import com.github.icarohs7.core.toplevel.onBg
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.getOrElse
import kotlinx.coroutines.experimental.Deferred

/**
 * Get request with embedded parsing of objects
 */
inline fun <reified T> String.httpGetObjectAsync(
        query: List<Pair<String, Any>> = emptyList(),
        body: String = "",
        noinline jsonTransformationBeforeParsing: (String) -> String = { it }
): Deferred<T?> {

    return onBg {
        try {
            Klaxon().parse<T>(
                    jsonTransformationBeforeParsing(
                            this.httpGet(query)
                                    .body(body)
                                    .awaitStringResult()
                                    .getOrElse("")
                                    .trim()))
        } catch (e: Exception) {
            null
        }
    }
}

/**
 * Get request with embedded parsing of arrays
 */
inline fun <reified T> String.httpGetArrayAsync(
        query: List<Pair<String, Any>> = emptyList(),
        body: String = "",
        noinline jsonTransformationBeforeParsing: (String) -> String = { it }
): Deferred<List<T>> {

    return onBg {
        try {
            Klaxon().parseArray<T>(
                    jsonTransformationBeforeParsing(
                            this@httpGetArrayAsync
                                    .httpGet(query)
                                    .body(body)
                                    .awaitStringResult()
                                    .getOrElse("")
                                    .trim()))!!
        } catch (e: Exception) {
            emptyList<T>()
        }
    }
}

/**
 * Post request with embedded parsing of objects
 */
inline fun <reified T> String.httpPostObjectAsync(
        query: List<Pair<String, Any>> = emptyList(),
        body: String = "",
        noinline jsonTransformationBeforeParsing: (String) -> String = { it }
): Deferred<T?> {

    return onBg {
        try {
            Klaxon().parse<T>(
                    jsonTransformationBeforeParsing(
                            this@httpPostObjectAsync
                                    .httpPost(query)
                                    .body(body)
                                    .awaitStringResult()
                                    .getOrElse("")
                                    .trim()))
        } catch (e: Exception) {
            null
        }
    }
}

/**
 * Get request with embedded parsing of arrays
 */
inline fun <reified T> String.httpPostArrayAsync(
        query: List<Pair<String, Any>> = emptyList(),
        body: String = "",
        noinline jsonTransformationBeforeParsing: (String) -> String = { it }
): Deferred<List<T>> {

    return onBg {
        try {
            Klaxon().parseArray<T>(
                    jsonTransformationBeforeParsing(
                            this@httpPostArrayAsync
                                    .httpPost(query)
                                    .body(body)
                                    .awaitStringResult()
                                    .getOrElse("")
                                    .trim()))!!
        } catch (e: Exception) {
            emptyList<T>()
        }
    }
}