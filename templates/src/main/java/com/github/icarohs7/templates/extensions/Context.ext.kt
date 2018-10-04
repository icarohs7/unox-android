package com.github.icarohs7.templates.extensions

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.github.icarohs7.templates.toplevel.getBinding
import org.jetbrains.anko.layoutInflater

/**
 * Return a inflated binding
 */
fun <T : ViewDataBinding> Context.inflateBinding(@LayoutRes layoutId: Int, parent: ViewGroup? = null) =
        getBinding<T>(this.layoutInflater, layoutId, parent)