package com.example.popularlibraries.user

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.UserRepo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {

    fun initList(list: List<GithubUser>)

    fun initInfo(user: GithubUser)

    fun loadingUserList()

    fun loadingUserListEnd()

    fun initRepoList(list: List<UserRepo>)

    fun displayForksCount(forksCount: Int)
}