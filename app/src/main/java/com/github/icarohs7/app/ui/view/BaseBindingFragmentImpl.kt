package com.github.icarohs7.app.ui.view

import com.github.icarohs7.app.R
import com.github.icarohs7.app.databinding.FragmentBaseBindingImplBinding
import com.github.icarohs7.unoxandroid.ui.fragments.BaseBindingFragment

class BaseBindingFragmentImpl : BaseBindingFragment<FragmentBaseBindingImplBinding>() {
    override fun getLayout(): Int {
        return R.layout.fragment_base_binding_impl
    }
}