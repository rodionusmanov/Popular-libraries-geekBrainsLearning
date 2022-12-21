package com.example.popularlibraries.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibraries.PopularLibrariesApp
import com.example.popularlibraries.core.BackPressedListener
import com.example.popularlibraries.core.network.NetworkProvider
import com.example.popularlibraries.databinding.UserListFragmentBinding
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.UserRepo
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackPressedListener {

    companion object {
        fun getInstance(): UserFragment {
            return UserFragment()
        }
    }

    private lateinit var viewBinding: UserListFragmentBinding

    private val adapter = UserAdapter{
        presenter.onItemClick(it)
    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            GithubRepositoryImpl(
                NetworkProvider.usersApi
            ),
            PopularLibrariesApp.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return UserListFragmentBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            githubUsersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            githubUsersRecyclerView.adapter = adapter
        }
    }

    override fun initList(list: List<GithubUser>) {
        adapter.users = list
    }

    override fun initInfo(user: GithubUser) {
//        nothing to do
    }

    override fun loadingUserList() {
        viewBinding.userListProgressBar.visibility = View.VISIBLE
    }

    override fun loadingUserListEnd() {
        viewBinding.userListProgressBar.visibility = View.GONE
    }

    override fun initRepoList(list: List<UserRepo>) {
//        nothing to do
    }

    override fun displayForksCount(forksCount: Int){
//        nothing to do
    }

    override fun onBackPressed() = presenter.onBackPressed()
}