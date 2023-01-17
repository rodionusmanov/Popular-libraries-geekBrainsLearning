package com.example.popularlibraries.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibraries.PopularLibrariesApp
import com.example.popularlibraries.core.BackPressedListener
import com.example.popularlibraries.core.utils.loadImage
import com.example.popularlibraries.databinding.UserInfoFragmentBinding
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.UserRepo
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserInfoFragment : MvpAppCompatFragment(), UserView, BackPressedListener {

    companion object {
        private const val ARG_LOGIN = "ARG_LOGIN"
        fun getInstance(login: String): UserInfoFragment {
            return UserInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LOGIN, login)
                }
            }
        }
    }

    private lateinit var viewBinding: UserInfoFragmentBinding

    private val adapter = RepoAdapter {
        presenter.onItemClick(it)
    }

    @Inject
    lateinit var userInfoPresenter: UserInfoPresenter

    private val presenter: UserInfoPresenter by moxyPresenter {
        PopularLibrariesApp.instance.applicationComponent.inject(this)
        userInfoPresenter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.getString(ARG_LOGIN)?.let {
            presenter.loadUser(it)
        }
        return UserInfoFragmentBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            userReposRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            userReposRecyclerView.adapter = adapter
        }
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun initInfo(user: GithubUser) {
        with(viewBinding) {
            userInfoLoginTv.text = user.login
            userAvatarIv.loadImage(user.avatarUrl)
        }
    }

    override fun loadingUserList() {
        viewBinding.userInfoProgressBar.visibility = View.VISIBLE
        viewBinding.userInfoCv.visibility = View.GONE
    }

    override fun loadingUserListEnd() {
        viewBinding.userInfoProgressBar.visibility = View.GONE
        viewBinding.userInfoCv.visibility = View.VISIBLE
    }

    override fun initList(list: List<GithubUser>) {
//      nothing to do
    }

    override fun initRepoList(list: List<UserRepo>) {
        adapter.repos = list
    }

    override fun displayForksCount(forksCount: Int){
        val dialog = ForksCounterDialogFragment(forksCount)
        dialog.show(requireActivity().supportFragmentManager, "forks count")
    }
}