package com.github.icarohs7.unoxcore.extensions

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
 * Return the first ocurrence of the substring
 * matching the Regex
 */
fun String.find(regex: Regex): String? =
        regex.find(this)?.value

/**
 * Return only the parts of the
 * string matching the given regex
 */
fun String.onlyMatching(regex: Regex): String {
    return regex.findAll(this).joinToString(separator = "") { it.value }
}

/**
 * Return a string with only the
 * digits contained in the original
 * string
 */
fun String.onlyNumbers(): String {
    val regex = Regex("""\d""")
    return this.onlyMatching(regex)
}

/**
 * Return all digits contained in
 * string, with the order retained
 */
fun String.digits(): Long? {
    return onlyNumbers().toLongOrNull()
}

/**
 * Split the string on each ocurrence of the given
 * [separator] and join each part using the same
 * separator, but capitalizing the first letter
 * of each element while converting the rest of
 * the letters on the element to lowercase
 */
fun String.capitalizeWords(separator: String = " "): String {
    return split(separator).joinToString(separator) { it.toLowerCase().capitalize() }
}

/**
 * Convert the given string to int
 * or return the [fallback] if not
 * possible
 */
fun String.toIntOr(fallback: Int): Int {
    return this.toIntOrNull().valueOr(fallback)
}

/**
 * Convert the given string to double
 * or return the [fallback] if not
 * possible
 */
fun String.toDoubleOr(fallback: Double): Double {
    return this.toDoubleOrNull().valueOr(fallback)
}