package com.example.popularlibraries.core.mapper

import com.example.popularlibraries.core.network.UserDTO
import com.example.popularlibraries.model.GithubUser

object UserMapper {

    fun mapToEntitty(dto: UserDTO): GithubUser{
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl
        )
    }

//    fun mapToDto()
}