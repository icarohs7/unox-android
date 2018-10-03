package com.github.icarohs7.core.typealiases

typealias Lambda = () -> Unit

typealias Predicate<T> = (T) -> Boolean
typealias RPredicate<T> = T.() -> Boolean

typealias Consumer<T> = (T) -> Unit
typealias RConsumer<T> = T.() -> Unit

typealias BiConsumer<T, U> = (T, U) -> Unit

typealias Transformer<T, R> = (T) -> R
typealias RTransformer<T, R> = T.() -> R

typealias Influencer<T> = (T) -> T
typealias RInfluencer<T> = T.() -> T