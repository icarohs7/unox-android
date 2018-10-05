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

package com.github.icarohs7.reactive.view.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.icarohs7.reactive.entities.ActivityResources
import com.github.icarohs7.reactive.settings.ContractWatcherSettings
import com.github.icarohs7.reactive.view.contract.Contract
import com.google.android.material.navigation.NavigationView

/**
 * Activity that listens to a LiveData of contracts and when it changes,
 * selects the menu item tied to it and runs the action in it
 */
abstract class ContractWatcherActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val contractDealer = ContractWatcherSettings.AppContractDealer
    val navigationResources = ActivityResources()

    /**
     * Called first when the activity is started to define the navigationResources used, like menus, title, etc
     */
    abstract fun onDefineActivityResources(): ActivityResources.() -> Unit

    /**
     * Called when the activity is created, content view should be
     * defined here
     */
    abstract fun onSetContentView()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSetContentView()
        onDefineActivityResources()(navigationResources)
        loadNavigationResources()
        savedInstanceState?.also(::recoverStateWhenExistent)
        observeContractChanges()
        observeMenuItemChanges()
        afterInitialSetup()
    }

    /**
     * Called whenever an item in your options menu is selected
     */
    override fun onOptionsItemSelected(item: MenuItem) = navigationResources.drawerLayout?.let { drawer ->
        if (item.itemId == android.R.id.home) {
            drawer.openDrawer(androidx.core.view.GravityCompat.START)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    } ?: super.onOptionsItemSelected(item)

    /**
     * Called when an item menu is selected
     */
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        navigationResources.drawerLayout?.closeDrawers()
        val contract = contractDealer.findContractByMenuItemId(menuItem.itemId)
        executeContract(contract)
        return true
    }

    /**
     * Called when there's state stored
     */
    open fun recoverStateWhenExistent(savedInstanceState: Bundle) {
    }

    /**
     * Called after View creation and initial setup done
     */
    open fun afterInitialSetup() {
    }

    /**
     * Called whenever the contract of the dealer changes
     */
    open fun executeContract(contract: Contract) {
        checkMenuItem(contract.menuItemId)
        contract.navigateAction(this)
    }

    /**
     * Check the menu item with the Id parameterized or do nothing if the item doesn't exist
     */
    open fun checkMenuItem(menuItemId: Int) {
        try {
            navigationResources.sideNavigationView?.setCheckedItem(menuItemId)
        } catch (e: IllegalArgumentException) {
        }
    }

    /**
     * Activity starts to observe and react to changes in the loaded contract
     */
    private fun observeContractChanges() {
        contractDealer.loadedContract.observe(this, Observer(::executeContract))
    }

    /**
     * Activity starts to observe and react to changes in the loaded menu item
     */
    private fun observeMenuItemChanges() {
        contractDealer.loadedMenuItemId.observe(this, Observer(::checkMenuItem))
    }

    /**
     * Load the resources defined
     */
    private fun loadNavigationResources() {
        val res = navigationResources

        //Bottom navigation
        res.bottomNavigationView
                ?.setOnNavigationItemSelectedListener(::onNavigationItemSelected)

        res.bottomNavigationMenuRes?.let {
            res.bottomNavigationView?.inflateMenu(it)
        }

        //Side navigation
        res.sideNavigationView
                ?.setNavigationItemSelectedListener(this)

        res.sideNavigationHeaderRes?.let {
            res.sideNavigationView?.inflateHeaderView(it)
        }

        res.sideNavigationMenuRes?.let {
            res.sideNavigationView?.inflateMenu(it)
        }

        //Toolbar
        res.toolbarTitle?.let {
            supportActionBar?.title = it
        }

        setSupportActionBar(res.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        res.toolbarOpenDrawerMenuItemDrawableId?.let {
            supportActionBar?.setHomeAsUpIndicator(it)
        }
    }

}