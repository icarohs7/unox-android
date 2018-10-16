package com.github.icarohs7.network.extensions

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import com.github.icarohs7.network.annotations.Label
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

/**
 * Convert an object to its json representation
 */
fun <T : Any> T?.toJson(): String {
    return this?.run { Klaxon().toJsonString(this) } ?: ""
}

/**
 * Return a map representation with the keys being the name of the
 * properties or the value of the annotation [Label] or the name
 * property of the [Json] annotation and the values being the value
 * of each property
 */
inline fun <reified T : Any> T.toMapFromProperties(): Map<String, String> {
    return T::class.memberProperties.map { prop ->

        val propertyLabel = prop.findAnnotation<Label>()?.value
                ?: prop.findAnnotation<Json>()?.name
                ?: prop.name
        val propertyValue = prop.get(this).toString()

        propertyLabel to propertyValue

    }.toMap()
}