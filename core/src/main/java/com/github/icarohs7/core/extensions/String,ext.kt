package com.github.icarohs7.core.extensions

/**
 * Returns a string containing only the numbers
 * in the original string (all that's not in \d)
 */
fun String.onlyNumbers(): String {
    return this.replace("[^\\d]".toRegex(), "")
}