package com.example.popularlibraries.core.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserDTO (
    @Expose
    @SerializedName("id")
    val id: Long,
    @Expose
    @SerializedName("login")
    val login: String,
    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String
)