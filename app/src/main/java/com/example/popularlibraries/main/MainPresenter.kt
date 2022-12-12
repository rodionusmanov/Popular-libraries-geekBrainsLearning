package com.example.popularlibraries.main

import com.example.popularlibraries.core.navigation.UsersScreen
import com.example.popularlibraries.repository.GithubRepository
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(
    private val router: Router
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreen)
    }

    fun onBackPressed() {
        router.exit()
    }
}