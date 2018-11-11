package com.github.icarohs7.library.view.activities

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

/**
 * Base activity class using databinding, viewmodel,
 * embedded composite disposable and implementing a
 * coroutine scope
 */
abstract class BaseFullNxActivity<DB : ViewDataBinding, VM : ViewModel> : BaseBindingAndResourceNxActivity<DB>() {
    abstract val viewModel: VM
}