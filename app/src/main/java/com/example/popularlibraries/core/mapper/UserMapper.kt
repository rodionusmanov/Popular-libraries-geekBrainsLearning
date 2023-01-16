package com.example.popularlibraries.core.mapper

import com.example.popularlibraries.core.database.RepoDBObject
import com.example.popularlibraries.core.database.UserDBObject
import com.example.popularlibraries.core.network.ReposDTO
import com.example.popularlibraries.core.network.UserDTO
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.UserRepo

class UserMapper {

    fun mapToEntity(dto: UserDTO): GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl,
            reposUrl = dto.reposUrl
        )
    }

    fun mapToEntityRepos(dto: ReposDTO): UserRepo {
        return UserRepo(
            name = dto.name,
            forksCount = dto.forksCount
        )
    }

    fun mapToEntityRepos(dbObject: RepoDBObject): UserRepo {
        return UserRepo(
            name = dbObject.name,
            forksCount = dbObject.forks
        )
    }

    fun mapToEntity(dbObject: UserDBObject): GithubUser {
        return GithubUser(
            id = dbObject.id,
            login = dbObject.login,
            avatarUrl = dbObject.avatarUrl,
            reposUrl = dbObject.reposUrl
        )
    }

    fun mapToDBObject(dto: UserDTO): UserDBObject {
        return UserDBObject(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl,
            reposUrl = dto.reposUrl
        )
    }
}