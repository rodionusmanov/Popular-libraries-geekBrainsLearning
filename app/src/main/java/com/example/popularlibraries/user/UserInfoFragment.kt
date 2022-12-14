package com.example.popularlibraries.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibraries.PopularLibrariesApp
import com.example.popularlibraries.core.BackPressedListener
import com.example.popularlibraries.databinding.UserInfoFragmentBinding
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.example.popularlibraries.utils.userPosition
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserInfoFragment() : MvpAppCompatFragment(), UserView, BackPressedListener {

    companion object {
        fun getInstance(): UserInfoFragment {
            return UserInfoFragment()
        }
    }

    private lateinit var viewBinding: UserInfoFragmentBinding

    private val presenter: UserInfoPresenter by moxyPresenter {
        UserInfoPresenter(GithubRepositoryImpl(), PopularLibrariesApp.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return UserInfoFragmentBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun initInfo(user: GithubUser) {
        with(viewBinding) {
            userInfoLoginTv.text = user.login
            userInfoTextTv.text = user.info
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
}