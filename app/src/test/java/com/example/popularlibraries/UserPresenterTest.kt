package com.example.popularlibraries

import com.example.popularlibraries.core.navigation.UserInfoScreen
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.example.popularlibraries.user.UserPresenter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserPresenterTest {

    private val gitHubUser = GithubUser(
        0,
        "login",
        "avatarUrl",
        "reposUrl",
        null
    )

    private val response = listOf(gitHubUser)
    private val emptyResponse = listOf<GithubUser>()

    private lateinit var presenter: UserPresenter

    @Mock
    private lateinit var repository: GithubRepositoryImpl

    @Mock
    private lateinit var router: Router

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UserPresenter(repository, router)
    }

    @Test
    fun onBackPressed_Test() {
        presenter.onBackPressed()
        Mockito.verify(router, Mockito.times(1)).exit()
    }

    @Test
    fun onItemClick_Test() {
        val login = "some login"
        presenter.onItemClick(login)
        Mockito.verify(router, Mockito.times(1)).navigateTo(UserInfoScreen(login))
    }

    @Test
    fun getData_NotEmptyListTest() {
        Mockito.`when`(repository.getUsers()).thenReturn(Single.just(response))
        val list = repository.getUsers().subscribeOn(Schedulers.io()).blockingGet()
        assertTrue(list.isNotEmpty())
    }

    @Test
    fun getData_EmptyListTest() {
        Mockito.`when`(repository.getUsers()).thenReturn(Single.just(emptyResponse))
        val list = repository.getUsers().subscribeOn(Schedulers.io()).blockingGet()
        assertTrue(list.isEmpty())
    }
}