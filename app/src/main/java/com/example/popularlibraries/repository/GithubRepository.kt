package com.example.popularlibraries.repository

import com.example.popularlibraries.model.GithubUser

interface GithubRepository {
    fun getUsers(): List<GithubUser>
}