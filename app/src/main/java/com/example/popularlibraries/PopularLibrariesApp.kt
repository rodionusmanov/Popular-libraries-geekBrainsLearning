package com.example.popularlibraries

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.popularlibraries.core.database.GithubAppDb
import com.example.popularlibraries.core.utils.ConnectivityListener
import com.example.popularlibraries.di.ApplicationComponent
import com.example.popularlibraries.di.ApplicationModule
import com.example.popularlibraries.di.DaggerApplicationComponent
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class PopularLibrariesApp : Application() {

    companion object {
        lateinit var instance: PopularLibrariesApp
    }

    lateinit var applicationComponent: ApplicationComponent

    val database by lazy { GithubAppDb.create(this) }

    private lateinit var connectivityListener: ConnectivityListener

    override fun onCreate() {
        super.onCreate()

        instance = this

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        connectivityListener = ConnectivityListener(
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )

        RxJavaPlugins.setErrorHandler {

        }
    }

    fun getConnectSingle() = connectivityListener.statusSingle()
}