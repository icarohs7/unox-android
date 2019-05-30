package com.github.icarohs7.unoxcore.testutils

import android.app.Application
import com.github.icarohs7.unoxcore.R

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_AppCompat)
    }
}