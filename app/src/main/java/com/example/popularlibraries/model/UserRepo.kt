package com.example.popularlibraries.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepo(
    val name: String,
    val forksCount: Int
) : Parcelable