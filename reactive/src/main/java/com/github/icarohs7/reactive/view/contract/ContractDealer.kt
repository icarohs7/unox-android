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

package com.github.icarohs7.reactive.view.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Implement this class in a singleton to store the navigation state
 * of your application
 */
abstract class ContractDealer {

    /**
     * Value observed by the activity
     */
    val loadedContract: LiveData<Contract> = MutableLiveData()

    /**
     * Loaded menu item, also observing the loaded contract
     * to automatically change with it
     */
    val loadedMenuItemId: LiveData<Int> = MutableLiveData()

    /**
     * Return a contract identified by its menuId
     */
    abstract fun findContractByMenuItemId(menuItemId: Int): Contract

    /**
     * Load a contract, notifying the observing activity
     */
    fun loadContract(contract: Contract) {
        (loadedContract as MutableLiveData).postValue(contract)
        loadMenuItem(contract.menuItemId)
    }

    /**
     * Reload a the currently loaded contract
     */
    fun reloadContract() {
        loadedContract.value?.let(::loadContract)
    }

    /**
     * Load a menu item
     */
    fun loadMenuItem(menuItemId: Int) {
        (loadedMenuItemId as MutableLiveData).postValue(menuItemId)
    }
}