package com.example.popularlibraries.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.popularlibraries.user.UserFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserFragment.getInstance()
    }
}