package com.example.popularlibraries.repository

import com.example.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubRepository {

    fun getUsers(): Single<List<GithubUser>>

    fun getUserById(login: String): Single<GithubUser>
}