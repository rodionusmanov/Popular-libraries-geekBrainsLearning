package com.example.popularlibraries.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

    var cicerone : Cicerone<Router> = Cicerone.create()

    @Provides
    fun provideCicerone(): Cicerone<Router> = cicerone

    @Provides
    @Singleton
    fun provideNavigationHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun provideRouter(): Router = cicerone.router
}