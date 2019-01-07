package com.github.icarohs7.app.ui.view

import android.view.MenuItem
import com.github.icarohs7.unoxandroid.extensions.views.loadFragmentWithoutBack
import com.github.icarohs7.unoxandroid.ui.activities.BaseStandardNxActivity

class BaseStandardNxActivityImpl : BaseStandardNxActivity() {
    override fun afterInitialSetup() {
        super.afterInitialSetup()
        loadFragmentWithoutBack(BaseBindingFragmentImpl())
    }

    override fun onSelectMenuItem(menuItemId: MenuItem): Boolean {
        TODO("not implemented")
    }
}