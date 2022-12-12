package com.example.popularlibraries.user

import com.example.popularlibraries.model.GithubUser
import java.text.FieldPosition

fun interface OnItemClick {
    fun onItemClick(position: Int)
}