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

package com.github.icarohs7.library.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.github.icarohs7.library.entities.ActivityResources
import com.google.android.material.navigation.NavigationView

/**
 * Activity that listens to a LiveData of contracts and when it changes,
 * selects the menu item tied to it and runs the action in it
 */
abstract class BaseBindingAndResourceNxActivity<B : ViewDataBinding>
    : BaseNxActivity(), NavigationView.OnNavigationItemSelectedListener {
    val navigationResources: ActivityResources = ActivityResources()

    /**
     * Initialized on [onCreate]
     */
    lateinit var binding: B
        protected set

    /**
     * Called when a menu item from either the side or bottom nav is selected
     */
    abstract fun onSelectMenuItem(menuItemId: MenuItem): Boolean

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayout())
        binding.setLifecycleOwner(this)
        onBindingCreated(savedInstanceState)
        onDefineActivityResources(navigationResources)
        onLoadNavigationResources()
        afterInitialSetup()
    }

    /**
     * Called on [onCreate], after the binding is inflated and defined
     * as content of the activity
     */
    open fun onBindingCreated(savedInstanceState: Bundle?) {
    }

    /**
     * Called after [onBindingCreated], used to define the resources used
     * by the activity through the activityResources object
     */
    abstract fun onDefineActivityResources(activityResources: ActivityResources)

    /**
     * Called after [onDefineActivityResources], used to load all
     * defined resources from the activityResources object
     */
    fun onLoadNavigationResources() {
        val res = navigationResources

        //Bottom navigation
        res.bottomNavigationView
                ?.setOnNavigationItemSelectedListener(::onNavigationItemSelected)

        res.bottomNavigationMenuRes?.let {
            res.bottomNavigationView?.inflateMenu(it)
        }

        //Side navigation
        res.navDrawerView
                ?.setNavigationItemSelectedListener(this)

        res.navDrawerHeaderRes?.let {
            res.navDrawerView?.inflateHeaderView(it)
        }

        res.navDrawerMenuRes?.let {
            res.navDrawerView?.inflateMenu(it)
        }

        //Toolbar
        setSupportActionBar(res.toolbar)

        res.toolbarTitle?.let {
            supportActionBar?.title = it
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        res.toolbarOpenDrawerMenuItemDrawableId?.let {
            supportActionBar?.setHomeAsUpIndicator(it)
        }
    }

    /**
     * Called after [onLoadNavigationResources], as the last step of
     * the [onCreate] method
     */
    open fun afterInitialSetup() {
    }

    /**
     * @return layout to setup data binding.
     */
    @LayoutRes
    abstract fun getLayout(): Int

    /**
     * Called whenever an item in your options menu is selected
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean = navigationResources.drawerLayout?.let { drawer ->
        if (item.itemId == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START)
            true
        } else onSelectMenuItem(item)
    } ?: super.onOptionsItemSelected(item)

    /**
     * Called when an item menu is selected
     */
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        navigationResources.drawerLayout?.closeDrawers()
        onCheckMenuItem(menuItem.itemId)
        return onSelectMenuItem(menuItem)
    }

    /**
     * Check the menu item with the Id parameterized or do nothing if the item doesn't exist
     */
    open fun onCheckMenuItem(menuItemId: Int) {
        try {
            navigationResources.navDrawerView?.setCheckedItem(menuItemId)
        } catch (e: IllegalArgumentException) {
        }
    }
}