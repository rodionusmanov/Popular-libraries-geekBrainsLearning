package com.example.popularlibraries.repository.impl

import com.example.popularlibraries.model.GithubUser

class GithubRepositoryImpl {

    private val repositories = listOf(
        GithubUser("Andy"),
        GithubUser("Bob"),
        GithubUser("Charlie"),
        GithubUser("Don"),
        GithubUser("Earl")
    )

    fun getUsers(): List<GithubUser>{
        return repositories
    }
}