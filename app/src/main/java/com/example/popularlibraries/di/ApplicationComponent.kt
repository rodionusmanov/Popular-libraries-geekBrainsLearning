package com.example.popularlibraries.di

import com.example.popularlibraries.core.database.UserDAO
import com.example.popularlibraries.user.UserFragment
import com.example.popularlibraries.user.UserInfoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GithubUsersListModule::class])
interface ApplicationComponent {
    fun injectUserFragment(fragment: UserFragment)
    fun injectUserInfoFragment(fragment: UserInfoFragment)
}