package com.github.icarohs7.core.extensions

/**
 * Transformer removing the trailing and leading spaces
 * and the Byte Order Mark (BOM) from a string
 */
fun String.trimAndRemoveBom(): String {
    return replace(65279.toChar().toString(), "").trim()
}

/**
 * Returns the parameterized string if the first is null
 * or blank
 */
infix fun String?.ifBlankOrNull(fallback: String): String {
    return if (this.isNullOrBlank()) fallback else "$this"
}

/**
 * Returns the parameterized string if the first is null
 * or empty
 */
infix fun String?.ifEmptyOrNull(fallback: String): String {
    return if (this.isNullOrEmpty()) fallback else "$this"
}