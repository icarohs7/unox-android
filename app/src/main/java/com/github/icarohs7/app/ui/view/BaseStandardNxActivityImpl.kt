package com.github.icarohs7.app.ui.view

import android.graphics.Color
import android.view.MenuItem
import com.github.icarohs7.unoxandroid.ui.activities.BaseStandardNxActivity
import org.jetbrains.anko.backgroundColor

class BaseStandardNxActivityImpl : BaseStandardNxActivity() {
    override fun afterInitialSetup() {
        super.afterInitialSetup()
        binding.containerActivitybasestandardnx.backgroundColor = Color.parseColor("#2222aa")
//        loadFragmentWithoutBack(TestFragment())
    }

    override fun onSelectMenuItem(menuItemId: MenuItem): Boolean {
        TODO("not implemented")
    }
}