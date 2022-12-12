package com.example.popularlibraries.repository.impl

import com.example.popularlibraries.model.GithubUser

class GithubRepositoryImpl {

    private val repositories = listOf(
        GithubUser("Andy", "Andy's info"),
        GithubUser("Bob", "Bob's info"),
        GithubUser("Charlie","Charlie's info"),
        GithubUser("Don", "Don's info"),
        GithubUser("Earl", "Earl's info")
    )

    fun getUsers(): List<GithubUser>{
        return repositories
    }
}