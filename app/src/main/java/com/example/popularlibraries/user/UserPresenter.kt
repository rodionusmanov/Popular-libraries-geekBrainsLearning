package com.example.popularlibraries.user

import com.example.popularlibraries.repository.GithubRepository
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    private val repository: GithubRepositoryImpl,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUsers())
    }

    fun onBackPressed(): Boolean{
        router.exit()
        return true
    }
}