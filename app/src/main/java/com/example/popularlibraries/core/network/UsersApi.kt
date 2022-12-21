package com.example.popularlibraries.core.network

import com.example.popularlibraries.model.UserRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {

    @GET("/users")
    fun getAllUsers(): Single<List<UserDTO>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login: String): Single<UserDTO>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): Single<List<ReposDTO>>
}