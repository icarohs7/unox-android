package com.github.icarohs7.visuals.view.fragments

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

/**
 * Base fragment class using databinding, viewmodel,
 * embedded composite disposable and implementing a
 * coroutine scope
 */
abstract class BaseFullNxFragment<DB : ViewDataBinding, VM : ViewModel> : BaseBindingFragment<DB>() {
    abstract val viewModel: VM
}