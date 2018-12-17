package com.github.icarohs7.library.ui.activities

import androidx.appcompat.app.AppCompatActivity
import com.github.icarohs7.library.extensions.coroutines.MainScope
import com.github.icarohs7.library.extensions.coroutines.cancelCoroutineScope
import kotlinx.coroutines.CoroutineScope

/**
 * Activity containing a coroutine scope,
 * cancelling it and all children coroutines
 * when destroyed
 */
abstract class ScopedActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutineScope()
    }
}