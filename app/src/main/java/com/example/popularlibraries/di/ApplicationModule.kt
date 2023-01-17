package com.example.popularlibraries.di

import com.example.popularlibraries.PopularLibrariesApp
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: PopularLibrariesApp) {

    @Provides
    fun app(): PopularLibrariesApp {
        return application
    }
}