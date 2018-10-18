/*
 * MIT License
 *
 * Copyright (c) 2018 Icaro R D Temponi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.icarohs7.adapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter based on Bindings
 */
abstract class BindingAdapter<T, DB : ViewDataBinding>(
        dataSource: List<T> = mutableListOf()
) : RecyclerView.Adapter<BindingAdapter.BindingViewHolder<DB>>() {

    /**
     * Property storing the data set, notifying the
     * adapter everytime it changes
     */
    var dataSource: List<T> = dataSource
        set(value) {
            val old = field
            field = value
            onDataSetChanged(old, value)
        }

    /**
     * Called everytime the data set changes
     */
    open fun onDataSetChanged(oldDataSet: List<T>, newDataSet: List<T>) {
        notifyDataSetChanged()
    }

    /**
     * Function converting an list item to an actual view
     */
    abstract fun itemToViewMapping(data: List<T>, position: Int, view: DB)

    /**
     * Resource id of the layout used for a single list item, which is repeated at the recycler
     */
    abstract val itemLayoutId: Int

    /**
     * Override to choose what operations should be performed on the list before showing it
     */
    open fun dataFilter(data: List<T>): List<T> =
            data

    /**
     * Creation of the viewholder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<DB> {
        val binding: DB = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                itemLayoutId,
                parent,
                false
        )

        return BindingViewHolder(binding)
    }

    /**
     * Setup of the viewholder when going to be visible
     */
    override fun onBindViewHolder(holder: BindingViewHolder<DB>, position: Int) {
        itemToViewMapping(dataFilter(dataSource), position, holder.binding)
    }

    /**
     * Size of the data set
     */
    override fun getItemCount(): Int =
            dataFilter(dataSource).size

    /**
     * Viewholder for the adapter
     */
    class BindingViewHolder<DB : ViewDataBinding>(val binding: DB) : RecyclerView.ViewHolder(binding.root)
}