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

package com.github.icarohs7.contractwatcher.view.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.icarohs7.contractwatcher.settings.ContractWatcherSettings
import com.github.icarohs7.core.extensions.valueTransaction
import com.github.icarohs7.core.toplevel.mutableLiveDataOf
import java.util.EmptyStackException
import java.util.Stack

/**
 * Implement this class in a singleton to store the navigation state
 * of your application
 */
abstract class ContractDealer {
    /**
     * Value observed by the activity
     */
    val loadedContract = MutableLiveData<Contract>()

    /**
     * Value observing the menu stack and storing the Id of the Menu Item corresponding to the screen currently loaded,
     * also observing the stack and reflecting changes
     */
    val selectedMenuItemId = MutableLiveData<Int>()

    /**
     * Stack storing menu changes and observing the loadedContract
     */
    private val menuItemStack = mutableLiveDataOf(Stack<Int>())

    init {
        loadedContract.observeForever { contract -> menuItemStack += contract.menuItemId }
        menuItemStack.observeForever { stack -> ignoreEmptyStack { selectedMenuItemId.postValue(stack.peek()) } }
    }

    /**
     * Return a contract identified by its menuId
     */
    abstract fun findContractByMenuItemId(menuItemId: Int): Contract

    /**
     * Load a contract, notifying the observing activity
     */
    fun loadContract(contract: Contract, async: Boolean = false) {
        if (async) {
            loadedContract.postValue(contract)
        } else {
            loadedContract.postValue(contract)
        }
    }

    /**
     * Remove the topmost item from the menu stack, notifying the stack's observers
     */
    internal fun popMenuItem() {
        menuItemStack.valueTransaction(failOnNullValue = ContractWatcherSettings.failNullLiveData) {
            ignoreEmptyStack { pop() }
        }
    }

    /**
     * Utility to easily push values to the stack
     */
    private operator fun <T> LiveData<Stack<T>>.plusAssign(value: T) {
        valueTransaction(failOnNullValue = ContractWatcherSettings.failNullLiveData) {
            push(value)
        }
    }

    /**
     * Function to ignore emptyStackExceptions
     */
    private inline fun ignoreEmptyStack(fn: () -> Unit) {
        try {
            fn()
        } catch (e: EmptyStackException) {
        }
    }
}