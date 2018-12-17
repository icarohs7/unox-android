package com.github.icarohs7.library.ui.fragments

import androidx.fragment.app.Fragment
import com.github.icarohs7.library.extensions.coroutines.MainScope
import com.github.icarohs7.library.extensions.coroutines.cancelCoroutineScope
import kotlinx.coroutines.CoroutineScope

/**
 * Fragment containing a coroutine scope,
 * cancelling it and all children coroutines
 * when destroyed
 */
abstract class ScopedFragment : Fragment(), CoroutineScope by MainScope() {
    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutineScope()
    }
}