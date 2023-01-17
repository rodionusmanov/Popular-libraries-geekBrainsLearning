package com.example.popularlibraries.main

import com.example.popularlibraries.PopularLibrariesApp
import com.example.popularlibraries.core.navigation.UsersScreen
import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var currentRouter: Router

    val router : Router by lazy {
        PopularLibrariesApp.instance.applicationComponent.inject(this)
        currentRouter
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreen)
    }

    fun onBackPressed() {
        router.exit()
    }
}