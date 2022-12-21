package com.example.popularlibraries.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val id: Long,
    val login: String,
    val avatarUrl: String?
) : Parcelable