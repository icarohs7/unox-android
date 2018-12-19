@file:JvmName("StringExt")

package com.github.icarohs7.unoxandroid.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.icarohs7.unoxandroid.extensions.coroutines.onBackground

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
 * Fetch an image from the path used as receiver
 * @return The [Drawable] of the image or null if it the image couldn't be fetched
 */
suspend fun String.fetchImage(context: Context, placeholder: Drawable? = null, error: Drawable? = null): Drawable? {
    return onBackground {
        Glide.with(context)
                .load(this@fetchImage)
                .also {
                    val options = RequestOptions().centerCrop()
                    placeholder?.let(options::placeholder)
                    error?.let(options::error)
                }
                .submit()
                .get()
    }
}