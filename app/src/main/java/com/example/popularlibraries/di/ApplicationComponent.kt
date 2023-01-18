package com.example.popularlibraries.di

import android.app.Application
import com.example.popularlibraries.PopularLibrariesApp
import com.example.popularlibraries.main.MainActivity
import com.example.popularlibraries.main.MainPresenter
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.example.popularlibraries.user.UserFragment
import com.example.popularlibraries.user.UserInfoFragment
import com.example.popularlibraries.user.UserPresenter
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PresentersModule::class,
        UserMapperModule::class,
        CiceroneModule::class,
        ApplicationModule::class
    ]
)
interface ApplicationComponent {
    fun inject(fragment: UserFragment)
    fun inject(fragment: UserInfoFragment)
    fun inject(githubRepositoryImpl: GithubRepositoryImpl)
    fun inject(application: PopularLibrariesApp)
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(presentersModule: PresentersModule)
}