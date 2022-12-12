package com.example.popularlibraries.user

import android.widget.Toast
import com.example.popularlibraries.core.navigation.UserInfoScreen
import com.example.popularlibraries.core.navigation.UsersScreen
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.repository.GithubRepository
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import java.text.FieldPosition

class UserPresenter(
    private val repository: GithubRepositoryImpl,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUsers())
    }

    fun itemClick() {
        router.replaceScreen(UserInfoScreen)
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}