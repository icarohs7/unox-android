package com.github.icarohs7.unoxandroid.extensions

import arrow.core.Try
import arrow.core.getOrElse
import com.jsoniter.JsonIterator
import com.jsoniter.output.JsonStream

typealias JsonInstance = com.jsoniter.any.Any

/** The given object in JSON form */
fun <T : Any> T.json(): Try<String> {
    return Try {
        JsonStream.serialize(this)
    }
}

/** Parse string to [JsonInstance] */
fun String.parseJson(additionalStringFilter: (String) -> String = { it }): Try<JsonInstance> {
    return Try { JsonIterator.deserialize(additionalStringFilter(this)) }
}

/** Json to string or empty string in case of error */
fun JsonInstance.str(): String = arrow.core.Try { "$this" }.orEmpty()

/** Json to int or 0 in case of error */
fun JsonInstance.int(default: Int = 0): Int = arrow.core.Try { this.toInt() }.getOrElse { default }

/** Json to double or 0.0 in case of error */
fun JsonInstance.double(default: Double = 0.0): Double = arrow.core.Try { this.toDouble() }.getOrElse { default }

/** Shorthand to map the value inside the typeclass */
operator fun Try<JsonInstance>.get(index: Int): Try<JsonInstance> = this.mapCatching { it[index] }

/** Shorthand to map the value inside the typeclass */
operator fun Try<JsonInstance>.get(vararg keys: Any): Try<JsonInstance> = this.mapCatching { it.get(*keys) }

/** Shorthand to map the value inside the typeclass */
operator fun Try<JsonInstance>.get(key: Any): Try<JsonInstance> = this.mapCatching { it[key] }

/** Convert JSON element to string or return an empty string in case of error */
fun Try<JsonInstance>.str(): String = this.mapCatching(JsonInstance::str).orEmpty()

/** Convert JSON element to int or return [default] in case of error */
fun Try<JsonInstance>.int(default: Int = 0): Int =
        this.mapCatching { it.int(default) }.getOrElse { default }

/** Convert JSON element to int or return [default] in case of error */
fun Try<JsonInstance>.double(default: Double = 0.0): Double =
        this.mapCatching { it.double(default) }.getOrElse { default }

/** Map wrapped value to list */
fun Try<JsonInstance>.asList(): Try<List<JsonInstance>> {
    return this.mapCatching { it.asList() }
}