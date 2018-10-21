package com.avenuecode.bprepared.view.activities

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.avenuecode.bprepared.R
import com.avenuecode.bprepared.databinding.ActivityMainBinding
import com.avenuecode.bprepared.view.fragment.MainListFragment
import com.avenuecode.bprepared.view.fragment.UserInfoFragment
import com.avenuecode.bprepared.viewmodels.MainActivityViewModel


import javax.inject.Inject

class MainActivity : BaseAcitvity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java) }

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
        startMainListFragment()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_list -> {
                startMainListFragment()
            }
            R.id.nav_profile -> {
                startUserInfoFragment()
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun startUserInfoFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = UserInfoFragment()
        transaction.replace(R.id.main_content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun startMainListFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = MainListFragment()
        transaction.replace(R.id.main_content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
