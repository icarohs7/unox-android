package com.github.icarohs7.core.extensions

import java.text.NumberFormat
import java.util.Locale

/**
 * Returns a string containing only the numbers
 * in the original string (all that's not in \d)
 */
fun String?.onlyNumbers(): String {
    return this?.replace("[^\\d.]".toRegex(), "") ?: ""
}

/**
 * Removes all non numeric characters from the string and
 * return the formatted version of the resulting currency
 * according to the language and country parameterized
 */
fun String?.toLocaleCurrency(language: String, country: String): String {
    return try {
        this?.onlyNumbers()
                ?.toDouble()
                .run {
                    NumberFormat
                            .getCurrencyInstance(Locale(language, country))
                            .format(this)
                }
    } catch (e: NumberFormatException) {
        this
    }?.trim() ?: ""
}

/**
 * Removes all non numeric characters from the string and
 * return the formatted version of the resulting number
 * according to the language and country parameterized
 */
fun String?.toLocaleNumber(language: String, country: String): String {
    return try {
        this?.onlyNumbers()
                ?.toDouble()
                .run {
                    NumberFormat
                            .getInstance(Locale(language, country))
                            .format(this)
                }
    } catch (e: NumberFormatException) {
        this
    }?.trim() ?: ""
}