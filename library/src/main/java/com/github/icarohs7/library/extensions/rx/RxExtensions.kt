package com.github.icarohs7.library.extensions.rx

import com.github.icarohs7.library.UnoxAndroid
import io.reactivex.disposables.CompositeDisposable

/**
 * Implementation of a [UnoxAndroid.DisposableEntity] by
 * a [CompositeDisposable]
 */
@Suppress("FunctionName")
fun CompositeDisposableEntity(): UnoxAndroid.DisposableEntity =
        object : UnoxAndroid.DisposableEntity {
            override val disposable: CompositeDisposable = CompositeDisposable()
        }