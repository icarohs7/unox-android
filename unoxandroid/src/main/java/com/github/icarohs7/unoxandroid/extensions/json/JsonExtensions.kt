package com.github.icarohs7.unoxandroid.extensions.json

import arrow.core.Try
import arrow.core.getOrElse
import com.github.icarohs7.unoxandroid.extensions.orEmpty
import com.jsoniter.JsonIterator

typealias JsonInstance = com.jsoniter.any.Any

/** Parse string to [JsonInstance] */
fun String.parseJson(additionalStringFilter: (String) -> String = { it }): Try<JsonInstance> {
    return Try { JsonIterator.deserialize(additionalStringFilter(this)) }
}

/** Json to string or empty string in case of error */
fun JsonInstance.str(): String = arrow.core.Try { "$this" }.orEmpty()

/** Json to int or 0 in case of error */
fun JsonInstance.int(default: Int = 0): Int = arrow.core.Try { this.toInt() }.getOrElse { default }