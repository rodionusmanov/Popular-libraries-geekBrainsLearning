package com.example.popularlibraries.repository.impl

import com.example.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl {

    private val repositories = listOf(
        GithubUser("Andy", "Andy's info"),
        GithubUser("Bob", "Bob's info"),
        GithubUser("Charlie","Charlie's info"),
        GithubUser("Don", "Don's info"),
        GithubUser("Earl", "Earl's info")
    )

    fun getUsers(): Single<List<GithubUser>> {
        return Single.create {
            it.onSuccess(repositories)
        }
    }

    fun getCurrentUser(id:Int): Single<GithubUser>{
        return Single.create {
            it.onSuccess(repositories[id])
        }
    }
}