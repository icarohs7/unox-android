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

package com.github.icarohs7.library.extensions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.transaction
import com.github.icarohs7.library.UnoxAndroid
import org.jetbrains.anko.inputMethodManager

/**
 * Load a fragment adding it to the backstack
 */
inline fun <reified T : Fragment> AppCompatActivity.loadFragment(
        destination: T,
        containerId: Int = UnoxAndroid.masterContainer ?: 0
) {

    fragmentTransactionAnimated {
        replace(containerId, destination)
        addToBackStack("fragment")
    }
}

/**
 * Load a fragment without adding it to the backstack
 */
inline fun <reified T : Fragment> AppCompatActivity.loadFragmentWithoutBack(
        destination: T,
        containerId: Int = UnoxAndroid.masterContainer ?: 0
) {

    fragmentTransactionAnimated { replace(containerId, destination) }
}

/**
 * Execute a fragment transaction with an animation, defined by
 * the settings on the [UnoxAndroid.Companion]
 */
fun AppCompatActivity.fragmentTransactionAnimated(fn: FragmentTransaction.() -> Unit) {
    supportFragmentManager.transaction {
        setCustomAnimations(
                UnoxAndroid.enterAnim,
                UnoxAndroid.exitAnim,
                UnoxAndroid.popEnterAnim,
                UnoxAndroid.popExitAnim)
        fn()
    }
}

/**
 * Dismiss the soft keyboard
 */
fun AppCompatActivity.hideKeyboard(containerId: Int) {
    inputMethodManager.hideSoftInputFromWindow(findViewById<View>(containerId)?.windowToken, 0)
}


/**
 * Dismiss the soft keyboard
 */
fun AppCompatActivity.hideKeyboard(container: View) {
    inputMethodManager.hideSoftInputFromWindow(container.windowToken, 0)
}