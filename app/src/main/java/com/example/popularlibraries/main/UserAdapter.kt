package com.example.popularlibraries.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibraries.R
import com.example.popularlibraries.model.GithubUser

class UserAdapter() :
    RecyclerView.Adapter<GithubUserViewHolder>() {

    var users: List<GithubUser> = emptyList()
        @SuppressLint("notifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.github_user_item, parent, false)
        return GithubUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

}

class GithubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvLogin by lazy { itemView.findViewById<TextView>(R.id.user_login_tv) }

    fun bind(item: GithubUser) = with(item) {
        tvLogin.text = login
    }
}