package com.github.icarohs7.unoxandroid.extensions

/**
 * Returns the parameterized string if the first is null
 * or blank
 */
infix fun String?.ifBlankOrNull(fallback: String): String {
    return if (this.isNullOrBlank()) fallback else "$this"
}

/**
 * Return the result of the invocation of the given
 * function if the receiver is blank or null
 */
inline infix fun String?.ifBlankOrNull(fallback: () -> String): String {
    return if (this.isNullOrBlank()) fallback() else "$this"
}


/**
 * Returns the parameterized string if the first is null
 * or empty
 */
infix fun String?.ifEmptyOrNull(fallback: String): String {
    return if (this.isNullOrEmpty()) fallback else "$this"
}

/**
 * Return the result of the invocation of the given
 * function if the receiver is empty or null
 */
inline infix fun String?.ifEmptyOrNull(fallback: () -> String): String {
    return if (this.isNullOrEmpty()) fallback() else "$this"
}

/**
 * Returns the first ocurrence of the substring
 * matching the Regex
 */
fun String.find(regex: Regex): String? =
        regex.find(this)?.value