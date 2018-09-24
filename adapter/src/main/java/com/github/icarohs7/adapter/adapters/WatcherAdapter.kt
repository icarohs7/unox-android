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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class WatcherAdapter<T, DB : ViewDataBinding> : RecyclerView.Adapter<WatcherAdapter.WatcherViewHolder<DB>>() {
    open val observer = Observer<List<T>> { newList -> calculateChanges(newList ?: emptyList()) }

    open fun dataFilter(data: List<T>): List<T> =
            data

    open fun diffCallback(oldList: List<T>, newList: List<T>): DiffUtil.Callback =
            WatcherDiffUtil(oldList, newList)

    abstract fun dataFactory(): MutableLiveData<List<T>>
    abstract fun itemToViewMapping(data: List<T>, position: Int, view: DB)
    abstract val itemLayoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatcherViewHolder<DB> {
        val binding: DB = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                itemLayoutId,
                parent,
                false
        )

        return WatcherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WatcherViewHolder<DB>, position: Int) {
        itemToViewMapping(dataFilter(dataFactory().value ?: emptyList()), position, holder.binding)
    }

    override fun getItemCount(): Int =
            dataFilter(dataFactory().value ?: emptyList()).size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        dataFactory().observeForever(observer)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        dataFactory().removeObserver(observer)
    }

    private fun calculateChanges(newList: List<T>) {
        val oldList = dataFilter(dataFactory().value ?: emptyList())
        val diffResult = DiffUtil.calculateDiff(diffCallback(oldList, newList))
        diffResult.dispatchUpdatesTo(this)
    }

    class WatcherViewHolder<DB : ViewDataBinding>(val binding: DB) : RecyclerView.ViewHolder(binding.root)

    open class WatcherDiffUtil<T>(private val oldList: List<T>, private val newList: List<T>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize(): Int =
                oldList.size


        override fun getNewListSize(): Int =
                newList.size

    }
}