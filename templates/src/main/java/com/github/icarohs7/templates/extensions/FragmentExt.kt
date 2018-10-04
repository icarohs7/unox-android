package com.github.icarohs7.templates.extensions

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Inflate a binding
 */
fun <T : ViewDataBinding> Fragment.inflateBinding(@LayoutRes layoutId: Int, parent: ViewGroup? = null): T {
    return requireContext().inflateBinding(layoutId, parent)
}