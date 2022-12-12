package com.example.popularlibraries.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibraries.R
import com.example.popularlibraries.databinding.GithubUserItemBinding
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.user.OnItemClick

class UserAdapter(private val callback: OnItemClick) :
    RecyclerView.Adapter<UserAdapter.GithubUserViewHolder>() {

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


    inner class GithubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: GithubUser) {
            GithubUserItemBinding.bind(itemView).apply {
                userLoginTv.text = user.login
                userCv.setOnClickListener {
                    callback.onItemClick(position)
                }
            }
        }
    }
}