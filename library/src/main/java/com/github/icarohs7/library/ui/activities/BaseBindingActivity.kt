package com.github.icarohs7.library.ui.activities

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingActivity<B : ViewDataBinding> : BaseNxActivity() {
    /**
     * Initialized in [onCreate]
     */
    lateinit var binding: B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayout())
        binding.setLifecycleOwner(this)
        onBindingCreated(savedInstanceState)
    }

    /**
     * Called after the databinding of the fragment is set
     */
    open fun onBindingCreated(savedInstanceState: Bundle?) {
    }

    /**
     * @return layout to setup data binding.
     */
    @LayoutRes
    abstract fun getLayout(): Int
}