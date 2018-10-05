package com.github.icarohs7.adapter.extensions

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import com.github.icarohs7.adapter.adapters.WatcherAdapter
import com.github.icarohs7.core.typealiases.BiConsumer
import com.github.icarohs7.core.typealiases.Influencer

/**
 * Create an adapter from a liveData of a list
 */
fun <T, DB : ViewDataBinding> LiveData<List<T>>.createAdapter(
        @LayoutRes layoutRes: Int,
        filter: Influencer<List<T>> = { it },
        mapping: BiConsumer<T, DB>
): WatcherAdapter<T, DB> {
    return object : WatcherAdapter<T, DB>() {
        override fun dataFactory(): LiveData<List<T>> {
            return this@createAdapter
        }

        override fun itemToViewMapping(data: List<T>, position: Int, view: DB) {
            mapping(data[position], view)
        }

        override fun dataFilter(data: List<T>): List<T> = filter(data)

        override val itemLayoutId: Int get() = layoutRes
    }
}