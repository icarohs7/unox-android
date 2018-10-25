package com.github.icarohs7.visuals

import android.app.Application

class DummyTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_AppCompat_NoActionBar)
    }
}