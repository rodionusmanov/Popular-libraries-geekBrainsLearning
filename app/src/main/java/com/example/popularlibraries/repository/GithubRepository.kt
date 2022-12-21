package com.example.popularlibraries.repository

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.UserRepo
import io.reactivex.rxjava3.core.Single

interface GithubRepository {

    fun getUsers(): Single<List<GithubUser>>

    fun getUserById(login: String): Single<GithubUser>

    fun getUserRepos(login: String): Single<List<UserRepo>>
}