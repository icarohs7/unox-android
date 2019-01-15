package com.github.icarohs7.unoxandroid.extensions.json

import arrow.core.Try
import arrow.core.getOrElse
import com.github.icarohs7.unoxandroid.extensions.mapCatching
import com.github.icarohs7.unoxandroid.extensions.orEmpty

/** Shorthand to map the value inside the typeclass */
operator fun Try<JsonInstance>.get(index: Int): Try<JsonInstance> = this.mapCatching { it[index] }

/** Shorthand to map the value inside the typeclass */
operator fun Try<JsonInstance>.get(vararg keys: Any): Try<JsonInstance> = this.mapCatching { it.get(*keys) }

/** Shorthand to map the value inside the typeclass */
operator fun Try<JsonInstance>.get(key: Any): Try<JsonInstance> = this.mapCatching { it[key] }

/** Convert JSON element to string or return an empty string in case of error */
fun Try<JsonInstance>.str(): String = this.mapCatching(JsonInstance::str).orEmpty()

/** Convert JSON element to int or return [default] in case of error */
fun Try<JsonInstance>.int(default: Int = 0): Int = this.mapCatching { it.int() }.getOrElse { default }