package com.github.icarohs7.library.ui.activities

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

/**
 * Base activity class using databinding, viewmodel,
 * embedded composite disposable and implementing a
 * coroutine scope
 */
abstract class BaseFullNxActivity<DB : ViewDataBinding, VM : ViewModel> : BaseBindingAndResourceNxActivity<DB>() {
    /**
     * Viewmodel used for the activity
     */
    val viewModel: VM by lazy { viewModelInstance() }

    /**
     * Define the instance of the viewmodel that will be used
     */
    abstract fun viewModelInstance(): VM
}