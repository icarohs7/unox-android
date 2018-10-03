package com.github.icarohs7.network.extensions

import com.beust.klaxon.Klaxon

/**
 * Convert an object to its json representation
 */
fun <T : Any> T?.toJson(): String {
    return this?.run { Klaxon().toJsonString(this) } ?: ""
}