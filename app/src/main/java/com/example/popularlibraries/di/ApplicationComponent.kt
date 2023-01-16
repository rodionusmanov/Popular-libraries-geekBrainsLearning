package com.example.popularlibraries.di

import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.example.popularlibraries.user.UserFragment
import com.example.popularlibraries.user.UserInfoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresentersModule::class, UserMapperModule::class])
interface ApplicationComponent {
    fun injectUserFragment(fragment: UserFragment)
    fun injectUserInfoFragment(fragment: UserInfoFragment)
    fun injectUserMapper(githubRepositoryImpl: GithubRepositoryImpl)
}