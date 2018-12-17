package com.github.icarohs7.app.ui.view

import com.github.icarohs7.app.R
import com.github.icarohs7.unoxandroid.databinding.ActivityBaseSplashBinding
import com.github.icarohs7.unoxandroid.extensions.views.navigateTo
import com.github.icarohs7.unoxandroid.ui.activities.BaseNxTimeoutActivity

class BaseNxTimeoutActivityImpl : BaseNxTimeoutActivity<ActivityBaseSplashBinding>(600) {
    override fun onTimeout() {
        navigateTo(BaseStandardNxActivityImpl::class, finishActivity = true)
    }

    override fun getLayout(): Int {
        return R.layout.activity_base_splash
    }
}