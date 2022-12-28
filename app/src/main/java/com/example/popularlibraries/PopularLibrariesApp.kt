package com.example.popularlibraries

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.popularlibraries.core.database.GithubAppDb
import com.example.popularlibraries.core.utils.ConnectivityListener
import com.example.popularlibraries.di.ApplicationComponent
import com.example.popularlibraries.di.DaggerApplicationComponent
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class PopularLibrariesApp : Application() {

    companion object {
        lateinit var instance: PopularLibrariesApp
        val applicationComponent: ApplicationComponent by lazy { DaggerApplicationComponent.create() }
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigationHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router

    val database by lazy { GithubAppDb.create(this) }

    private lateinit var connectivityListener: ConnectivityListener

    override fun onCreate() {
        super.onCreate()

        instance = this

        connectivityListener = ConnectivityListener(
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )

        RxJavaPlugins.setErrorHandler {

        }
    }

    fun getConnectSingle() = connectivityListener.statusSingle()
}