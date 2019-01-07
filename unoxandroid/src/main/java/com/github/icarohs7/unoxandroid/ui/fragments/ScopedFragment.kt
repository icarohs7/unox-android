package com.github.icarohs7.unoxandroid.ui.fragments

import androidx.fragment.app.Fragment
import com.github.icarohs7.unoxandroid.extensions.coroutines.cancelCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

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