package com.example.popularlibraries.model

import android.os.Parcelable
import com.example.popularlibraries.repository.GithubRepository
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val id: Long,
    val login: String,
    val avatarUrl: String?,
    val reposUrl: String,
    var repos: List<UserRepo>? = null
) : Parcelable