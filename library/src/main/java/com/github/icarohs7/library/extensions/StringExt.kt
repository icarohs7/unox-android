package com.github.icarohs7.library.extensions

import arrow.core.Option
import arrow.core.Try
import arrow.core.orNull
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

/**
 * Returns the first ocurrence of the substring
 * matching the Regex
 */
fun String.find(regex: Regex): String? =
        regex.find(this)?.value

/**
 * Parse a string in the given format to a [Option] of a [Date]
 */
fun String.parseDate(format: String = "yyyy-MM-dd HH:mm:ss"): Try<Date> = Try {
    SimpleDateFormat(format, Locale.getDefault()).parse(this)!!
}

/**
 * Format a date string to another date format, returning
 * the string option
 */
fun String.toStringDateWithFormat(
        format: String = "yyyy-MM-dd HH:mm:ss",
        oldFormat: String = "yyyy-MM-dd HH:mm:ss"
): Try<String> = Try {
    val locale = Locale.getDefault()
    val date = parseDate(oldFormat).orNull()!!
    SimpleDateFormat(format, locale).format(date)!!
}