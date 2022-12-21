package com.example.popularlibraries.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibraries.core.utils.loadImage
import com.example.popularlibraries.databinding.GithubUserItemBinding
import com.example.popularlibraries.model.GithubUser

typealias OnUserClickListener = (login: String) -> Unit

class UserAdapter(
    private val onUserClickListener: OnUserClickListener
) :
    RecyclerView.Adapter<UserAdapter.GithubUserViewHolder>() {

    var users: List<GithubUser> = emptyList()
        @SuppressLint("notifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        val binding =
            GithubUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GithubUserViewHolder(binding, onUserClickListener)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    inner class GithubUserViewHolder(
        private val binding: GithubUserItemBinding,
        private val onUserClickListener: OnUserClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: GithubUser) = with(binding) {
            userLoginTv.text = user.login

            githubUserIv.loadImage(user.avatarUrl)

            root.setOnClickListener {
                onUserClickListener.invoke(user.login)
            }
        }
    }
}