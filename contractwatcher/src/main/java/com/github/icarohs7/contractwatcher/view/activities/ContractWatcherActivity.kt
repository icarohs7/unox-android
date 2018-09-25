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

package com.github.icarohs7.contractwatcher.view.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import com.github.icarohs7.contractwatcher.settings.ContractWatcherSettings
import com.github.icarohs7.contractwatcher.view.contract.Contract
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

abstract class ContractWatcherActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val contractDealer = ContractWatcherSettings.AppContractDealer

    open var sideNavigationView: NavigationView? = null
    open var bottomNavigationView: BottomNavigationView? = null
    open var toolbar: Toolbar? = null
    open var toolbarOpenDrawerMenuItemDrawableId: Int? = null
    open var drawerLayout: DrawerLayout? = null

    /**
     * Called when the activity is created, content view should be
     * defined here
     */
    abstract fun onSetContentView()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSetContentView()
        initSideNavigation()
        initBottomNavigation()
        initToolbar()
        savedInstanceState?.also { state -> recoverStateWhenExistent(state) }
        observeContractChanges()
        observeFragmentStackChanges()
        afterInitialSetup()
    }

    /**
     * Called whenever an item in your options menu is selected
     */
    override fun onOptionsItemSelected(item: MenuItem) = drawerLayout?.let { drawer ->
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
        drawerLayout?.closeDrawers()
        val contract = contractDealer.findContractByMenuItemId(menuItem.itemId)
        executeContract(contract)
        return true
    }

    /**
     * Side navigation menu (drawer) initialization process
     */
    open fun initSideNavigation() = sideNavigationView?.also { navView ->
        navView.setNavigationItemSelectedListener(this)
    }

    /**
     * Bottom navigation view initialization process
     */
    open fun initBottomNavigation() = bottomNavigationView?.also { bottomNav ->
        bottomNav.setOnNavigationItemSelectedListener(::onNavigationItemSelected)
    }

    /**
     * Toolbar initialization process
     */
    open fun initToolbar() = toolbar?.also { toolbar ->
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarOpenDrawerMenuItemDrawableId?.let(supportActionBar!!::setHomeAsUpIndicator)
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
        contract.navigateAction(this)
    }

    /**
     * Check the menu item with the Id parameterized or do nothing if the item doesn't exist
     */
    open fun checkMenuItem(menuItemId: Int) {
        try {
            sideNavigationView?.apply { setCheckedItem(menuItemId) }
        } catch (e: IllegalArgumentException) {
        }
    }

    /**
     * Activity starts to observe and react to changes at liveDatas at the dealer
     */
    private fun observeContractChanges() {
        contractDealer.loadedContract.observe(this, Observer(::executeContract))
        contractDealer.selectedMenuItemId.observe(this, Observer(::checkMenuItem))
    }

    /**
     * Activity starts to observe the fragment stack and pop menu items as the fragments are popped from the backstack
     */
    private fun observeFragmentStackChanges() {
        supportFragmentManager.addOnBackStackChangedListener(contractDealer::popMenuItem)
    }
}