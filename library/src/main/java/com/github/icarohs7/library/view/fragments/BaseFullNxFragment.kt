package com.github.icarohs7.library.view.fragments

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

/**
 * Base fragment class using databinding, viewmodel,
 * embedded composite disposable and implementing a
 * coroutine scope
 */
abstract class BaseFullNxFragment<DB : ViewDataBinding, VM : ViewModel> : BaseBindingFragment<DB>() {
    /**
     * Viewmodel used for the fragment
     */
    val viewModel: VM by lazy { viewModelInstance() }

    /**
     * Define the instance of the viewmodel that will be used
     */
    abstract fun viewModelInstance(): VM
}