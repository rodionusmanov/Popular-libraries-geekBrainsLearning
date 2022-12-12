package com.example.popularlibraries.main

import android.os.Bundle
import com.example.popularlibraries.PopularLibrariesApp
import com.example.popularlibraries.R
import com.example.popularlibraries.core.BackPressedListener
import com.example.popularlibraries.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container_main)
    private lateinit var binding: ActivityMainBinding

    private val presenter by moxyPresenter { MainPresenter(PopularLibrariesApp.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        PopularLibrariesApp.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        PopularLibrariesApp.instance.navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { currentFragment ->
            if (currentFragment is BackPressedListener && currentFragment.onBackPressed()) {
                return
            }
        }
        presenter.onBackPressed()
    }
}