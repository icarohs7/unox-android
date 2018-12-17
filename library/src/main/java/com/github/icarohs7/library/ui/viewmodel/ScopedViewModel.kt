package com.github.icarohs7.library.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.github.icarohs7.library.extensions.coroutines.MainScope
import com.github.icarohs7.library.extensions.coroutines.cancelCoroutineScope
import kotlinx.coroutines.CoroutineScope

/**
 * Base viewmodel class with a coroutine scope,
 * cancelling all coroutines when cleared
 */
abstract class ScopedViewModel : ViewModel(), CoroutineScope by MainScope() {
    override fun onCleared() {
        super.onCleared()
        cancelCoroutineScope()
    }
}