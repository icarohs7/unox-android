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
import com.github.icarohs7.core.extensions.trimAndRemoveBom
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.map

/**
 * Get request with embedded parsing of objects
 */
suspend inline fun <reified T : Any> String.httpGetObject(
        query: List<Pair<String, Any>> = emptyList(),
        body: String = "",
        noinline jsonTransformBeforeParse: (String) -> String = { json -> json.trimAndRemoveBom() }
): Result<T, FuelError> {

    return this
            .httpGet(query)
            .body(body)
            .awaitStringResult()
            .map { it.parseJsonToObj<T>(jsonTransformBeforeParse)!! }
}


/**
 * Get request with embedded parsing of arrays
 */
suspend inline fun <reified T> String.httpGetArray(
        query: List<Pair<String, Any>> = emptyList(),
        body: String = "",
        noinline jsonTransformBeforeParse: (String) -> String = { json -> json.trimAndRemoveBom() }
): Result<List<T>, FuelError> {
    return this
            .httpGet(query)
            .body(body)
            .awaitStringResult()
            .map { it.parseJsonToArray<T>(jsonTransformBeforeParse)!! }
}


/**
 * Post request with embedded parsing of objects
 */
suspend inline fun <reified T : Any> String.httpPostObject(
        query: List<Pair<String, Any>> = emptyList(),
        body: String = "",
        noinline jsonTransformBeforeParse: (String) -> String = { json -> json.trimAndRemoveBom() }
): Result<T, FuelError> {

    return this.httpPost(query)
            .body(body)
            .awaitStringResult()
            .map { it.parseJsonToObj<T>(jsonTransformBeforeParse)!! }
}


/**
 * Get request with embedded parsing of arrays
 */
suspend inline fun <reified T> String.httpPostArray(
        query: List<Pair<String, Any>> = emptyList(),
        body: String = "",
        noinline jsonTransformBeforeParse: (String) -> String = { json -> json.trimAndRemoveBom() }
): Result<List<T>, FuelError> {

    return this.httpPost(query)
            .body(body)
            .awaitStringResult()
            .map { it.parseJsonToArray<T>(jsonTransformBeforeParse)!! }
}


/**
 * Parse a json object to a Kotlin object or return null in case of error parsing
 */
inline fun <reified T> String.parseJsonToObj(
        jsonTransformBeforeParse: (String) -> String = { json -> json.trimAndRemoveBom() }
): T? {
    return try {
        Klaxon().parse(jsonTransformBeforeParse(this))
    } catch (e: Exception) {
        null
    }
}

/**
 * Parse a json array to a Kotlin list or return an empty list in case of error parsing
 */
inline fun <reified T> String.parseJsonToArray(
        jsonTransformBeforeParse: (String) -> String = { json -> json.trimAndRemoveBom() }
): List<T>? {
    return try {
        Klaxon().parseArray(jsonTransformBeforeParse(this))
    } catch (e: Exception) {
        null
    }
}