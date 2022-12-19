package com.example.popularlibraries.main

import com.example.popularlibraries.core.navigation.ImageConverterScreen
import com.example.popularlibraries.core.navigation.UsersScreen
import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(
    private val router: Router
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(ImageConverterScreen)
    }

    fun onBackPressed() {
        router.exit()
    }
}