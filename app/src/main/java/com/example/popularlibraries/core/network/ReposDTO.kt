package com.example.popularlibraries.core.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReposDTO(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("forks_count")
    val forksCount: Int
)