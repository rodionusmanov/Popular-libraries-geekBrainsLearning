package com.example.popularlibraries.core.mapper

import com.example.popularlibraries.core.network.ReposDTO
import com.example.popularlibraries.core.network.UserDTO
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.UserRepo

object UserMapper {

    fun mapToEntitty(dto: UserDTO): GithubUser{
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl,
            reposUrl = dto.reposUrl
        )
    }

    fun mapToEntittyRepos(dto: ReposDTO): UserRepo{
        return UserRepo(
            name = dto.name,
            forksCount = dto.forksCount
        )
    }
}