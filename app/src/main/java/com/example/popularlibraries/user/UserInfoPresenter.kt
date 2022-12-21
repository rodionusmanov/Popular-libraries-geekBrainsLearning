package com.example.popularlibraries.user

import com.example.popularlibraries.core.navigation.UsersScreen
import com.example.popularlibraries.core.utils.disposeBy
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.example.popularlibraries.core.utils.fakeDelay
import com.example.popularlibraries.core.utils.subscribeByDefault
import com.example.popularlibraries.core.utils.userPosition
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class UserInfoPresenter(
    private val repository: GithubRepositoryImpl,
    private val router: Router
) : MvpPresenter<UserView>() {

    private val bag = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun loadUser(login: String) {
        viewState.loadingUserList()
        repository.getUserById(login)
            .subscribeByDefault()
            .delay(fakeDelay.toLong(), TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.initInfo(it)
                    viewState.loadingUserListEnd()
                }, {}
            ).disposeBy(bag)
    }

    fun onBackPressed(): Boolean {
        router.replaceScreen(UsersScreen)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
}