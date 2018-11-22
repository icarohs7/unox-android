package com.github.icarohs7.library.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import arrow.core.Try
import arrow.core.getOrElse
import arrow.core.orNull
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.icarohs7.library.onBackground
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
 * Parse a string in the given format
 */
fun String.parseDate(format: String = "yyyy-MM-dd HH:mm:ss"): Try<Date> = Try {
    SimpleDateFormat(format, Locale.getDefault()).parse(this)!!
}

/** Parse a date to its miliseconds value without unwrapping it */
fun String.parseDateMilisTry(format: String = "yyyy-MM-dd HH:mm:ss"): Try<Long> =
        parseDate(format).map { it.time }

/** Parse a date to its miliseconds value or return 0 if failed */
fun String.parseDateMilis(format: String = "yyyy-MM-dd HH:mm:ss"): Long =
        parseDateMilisTry(format).getOrElse { 0 }

/**
 * Format a date string to another date format
 */
fun String.toStringDateWithFormat(
        format: String = "yyyy-MM-dd HH:mm:ss",
        oldFormat: String = "yyyy-MM-dd HH:mm:ss"
): Try<String> = Try {
    val locale = Locale.getDefault()
    val date = parseDate(oldFormat).orNull()
    SimpleDateFormat(format, locale).format(date)!!
}

/**
 * Fetch an image from the path used as receiver
 * @return The [Drawable] of the image or null if it the image couldn't be fetched
 */
suspend fun String.fetchImage(context: Context, placeholder: Drawable? = null, error: Drawable? = null): Drawable? {
    return onBackground {
        Glide.with(context)
                .load(this)
                .also {
                    val options = RequestOptions().centerCrop()
                    placeholder?.let(options::placeholder)
                    error?.let(options::error)
                }
                .submit()
                .get()
    }
}