package com.github.icarohs7.library.ui.fragments

import androidx.fragment.app.Fragment
import com.github.icarohs7.library.UnoxAndroid
import com.github.icarohs7.library.extensions.coroutines.MainScope
import com.github.icarohs7.library.extensions.coroutines.cancelCoroutineScope
import com.github.icarohs7.library.extensions.rx.CompositeDisposableEntity
import kotlinx.coroutines.CoroutineScope

/**
 * Fragment containing a composite disposable
 * and a coroutine context, cancelling them when
 * stopped
 */
abstract class BaseNxFragment :
        Fragment(),
        CoroutineScope by MainScope(),
        UnoxAndroid.DisposableEntity by CompositeDisposableEntity() {
    /**
     * Cancel all coroutines and dispose all
     * subscriptions when destroyed
     */
    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutineScope()
        disposeSubscriptions()
    }
}