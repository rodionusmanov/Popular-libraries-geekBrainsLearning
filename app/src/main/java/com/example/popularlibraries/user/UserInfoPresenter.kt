package com.example.popularlibraries.user

import com.example.popularlibraries.core.navigation.UsersScreen
import com.example.popularlibraries.core.utils.disposeBy
import com.example.popularlibraries.core.utils.fakeDelay
import com.example.popularlibraries.core.utils.subscribeByDefault
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class UserInfoPresenter(
    private val repository: GithubRepositoryImpl,
    private val router: Router,
    private val mainThreadScheduler: Scheduler
) : MvpPresenter<UserView>() {

    private val bag = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun loadUser(login: String) {
        viewState.loadingUserList()
        repository.getUserById(login)
            .subscribeByDefault()
            .delay(fakeDelay.toLong(), TimeUnit.SECONDS, mainThreadScheduler)
            .subscribe(
                {
                    viewState.initInfo(it)
                    viewState.loadingUserListEnd()
                }, {}
            ).disposeBy(bag)
        repository.getUserRepos(login)
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initRepoList(it)
                }, {}
            ).disposeBy(bag)
    }

    fun onBackPressed(): Boolean {
        router.replaceScreen(UsersScreen)
        return true
    }

    fun onItemClick(forksCount: Int) {
        viewState.displayForksCount(forksCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
}