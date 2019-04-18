package com.github.icarohs7.unoxcore.extensions

import arrow.core.Try
import arrow.core.getOrElse
import java.text.NumberFormat

/** Convert the double value to it's currency form using default locale format */
fun Double.asCurrency(): String =
        Try { NumberFormat.getCurrencyInstance().format(this) }
                .getOrElse { this.toString() }