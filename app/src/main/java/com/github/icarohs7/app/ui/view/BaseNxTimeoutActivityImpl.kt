package com.github.icarohs7.app.ui.view

import com.github.icarohs7.app.R
import com.github.icarohs7.library.databinding.ActivityBaseSplashBinding
import com.github.icarohs7.library.extensions.views.navigateTo
import com.github.icarohs7.library.ui.activities.BaseNxTimeoutActivity

class BaseNxTimeoutActivityImpl : BaseNxTimeoutActivity<ActivityBaseSplashBinding>(600) {
    override fun onTimeout() {
        navigateTo(BaseStandardNxActivityImpl::class, finishActivity = true)
    }

    override fun getLayout(): Int {
        return R.layout.activity_base_splash
    }
}