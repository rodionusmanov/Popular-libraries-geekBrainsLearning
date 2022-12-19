package com.example.popularlibraries.user

import com.example.popularlibraries.core.navigation.UserInfoScreen
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.example.popularlibraries.utils.fakeDelay
import com.example.popularlibraries.utils.userPosition
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class UserPresenter(
    private val repository: GithubRepositoryImpl,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.loadingUserList()
        repository.getUsers()
            .subscribeOn(Schedulers.io())
            .delay(fakeDelay.toLong(), TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.initList(it)
                    viewState.loadingUserListEnd()
                }, {}
            )
    }

    fun itemClick(position: Int) {
        userPosition = position
        router.replaceScreen(UserInfoScreen)
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}