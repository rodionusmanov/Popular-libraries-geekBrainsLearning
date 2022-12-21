package com.example.popularlibraries.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibraries.databinding.RepoItemBinding
import com.example.popularlibraries.model.UserRepo

typealias OnRepoClickListener = (forksCount: Int) -> Unit

class RepoAdapter(
    private val onRepoClickListener: OnRepoClickListener
) :
    RecyclerView.Adapter<RepoAdapter.UserRepoViewHolder>() {

    var repos: List<UserRepo> = emptyList()
        @SuppressLint("notifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        val binding =
            RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserRepoViewHolder(binding, onRepoClickListener)
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount() = repos.size

    inner class UserRepoViewHolder(
        private val binding: RepoItemBinding,
        private val onRepoClickListener: OnRepoClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: UserRepo) = with(binding) {
            repoTv.text = repo.name
            root.setOnClickListener {
                onRepoClickListener.invoke(repo.forksCount)
            }
        }
    }
}