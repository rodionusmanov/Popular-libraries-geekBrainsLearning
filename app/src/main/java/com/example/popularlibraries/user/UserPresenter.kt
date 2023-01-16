package com.example.popularlibraries.user

import com.example.popularlibraries.core.navigation.UserInfoScreen
import com.example.popularlibraries.core.utils.fakeDelay
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserPresenter(
    private val repository: GithubRepositoryImpl,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.loadingUserList()
        repository
            .getUsers()
            .subscribeOn(Schedulers.io())
            .delay(fakeDelay.toLong(), TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.initList(it)
                    viewState.loadingUserListEnd()
                }, {}
            )
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun onItemClick(login: String) {
        router.navigateTo(UserInfoScreen(login))
    }
}