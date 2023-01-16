package com.example.popularlibraries.di

<<<<<<< HEAD
import android.app.Application
import com.example.popularlibraries.PopularLibrariesApp
import com.example.popularlibraries.main.MainActivity
import com.example.popularlibraries.main.MainPresenter
=======
>>>>>>> a09c566 (8 ДЗ. Добавил компонент для зависимости класса UserMapper.)
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.example.popularlibraries.user.UserFragment
import com.example.popularlibraries.user.UserInfoFragment
import com.example.popularlibraries.user.UserPresenter
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
<<<<<<< HEAD
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
=======
@Component(modules = [PresentersModule::class, UserMapperModule::class])
interface ApplicationComponent {
    fun injectUserFragment(fragment: UserFragment)
    fun injectUserInfoFragment(fragment: UserInfoFragment)
    fun injectUserMapper(githubRepositoryImpl: GithubRepositoryImpl)
>>>>>>> a09c566 (8 ДЗ. Добавил компонент для зависимости класса UserMapper.)
}