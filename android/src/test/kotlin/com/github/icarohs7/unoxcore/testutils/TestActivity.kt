package com.github.icarohs7.unoxcore.testutils

import androidx.appcompat.app.AppCompatActivity
import com.github.icarohs7.unoxcore.extensions.coroutines.cancelCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class TestActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onDestroy() {
        cancelCoroutineScope()
        super.onDestroy()
    }
}