package com.github.icarohs7.library.extensions

import arrow.core.Try
import arrow.core.getOrElse
import arrow.core.orNull
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Parse a string in the given format
 */
fun Try<String>.parseDate(format: String = "yyyy-MM-dd HH:mm:ss"): Try<Date> = Try {
    SimpleDateFormat(format, Locale.getDefault()).parse(this@parseDate.orNull())!!
}

/** Parse a date to its miliseconds value without unwrapping it */
fun Try<String>.parseDateMilisTry(format: String = "yyyy-MM-dd HH:mm:ss"): Try<Long> =
        parseDate(format).map { it.time }

/** Parse a date to its miliseconds value or return 0 if failed */
fun Try<String>.parseDateMilis(format: String = "yyyy-MM-dd HH:mm:ss"): Long =
        parseDateMilisTry(format).getOrElse { 0 }