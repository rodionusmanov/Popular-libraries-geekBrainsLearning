package com.example.popularlibraries.repository.impl

import com.example.popularlibraries.core.mapper.UserMapper
import com.example.popularlibraries.core.network.UsersApi
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.UserRepo
import com.example.popularlibraries.repository.GithubRepository
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl constructor(
    private val usersApi: UsersApi
) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> {
        return usersApi.getAllUsers()
            .map { it.map(UserMapper::mapToEntitty) }
    }

    override fun getUserById(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(UserMapper::mapToEntitty)
    }

    override fun getUserRepos(login: String): Single<List<UserRepo>> {
        return usersApi.getRepos(login)
            .map { it.map(UserMapper::mapToEntittyRepos) }
    }
}