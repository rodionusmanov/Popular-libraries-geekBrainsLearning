package com.example.popularlibraries.user

import com.example.popularlibraries.core.navigation.UsersScreen
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.example.popularlibraries.utils.userPosition
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserInfoPresenter(
    private val repository: GithubRepositoryImpl,
    private val router: Router,
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initInfo(repository.getUsers()[userPosition])
    }

    fun onBackPressed(): Boolean {
        router.replaceScreen(UsersScreen)
        return true
    }
}