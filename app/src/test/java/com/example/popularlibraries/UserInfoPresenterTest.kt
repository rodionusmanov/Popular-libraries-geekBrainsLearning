package com.example.popularlibraries

import com.example.popularlibraries.core.navigation.UsersScreen
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.UserRepo
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.example.popularlibraries.user.UserInfoPresenter
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class UserInfoPresenterTest {

    private val gitHubUser = GithubUser(
        0,
        "login",
        "avatarUrl",
        "reposUrl",
        null
    )

    private val userRepo = UserRepo(
        "name",
        1
    )

    private val repoResponse = listOf(userRepo)
    private val emptyRepoResponse = listOf<UserRepo>()

    private lateinit var presenter: UserInfoPresenter

    @Mock
    private lateinit var repository: GithubRepositoryImpl

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var mainThreadScheduler: Scheduler

    @Mock
    private lateinit var usersScreen: FragmentScreen

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UserInfoPresenter(repository, router, mainThreadScheduler)
    }

    @Test
    fun onBackPressed_Test() {
        usersScreen = UsersScreen
        presenter.onBackPressed()
        Mockito.verify(router, Mockito.times(1)).replaceScreen(usersScreen)
    }

    @Test
    fun onItemClickMock_VerifyTest() {
        val forksCount = 1
        val presenterMock =
            mock(UserInfoPresenter(repository, router, mainThreadScheduler).javaClass)
        presenterMock.onItemClick(forksCount)
        Mockito.verify(presenterMock, Mockito.times(1)).onItemClick(forksCount)
    }

    @Test
    fun onItemClickMock_NotNullForks_Test() {
        val forksCount = 1
        val presenterMock =
            mock(UserInfoPresenter(repository, router, mainThreadScheduler).javaClass)
        assertNotNull(presenterMock.onItemClick(forksCount))
    }

    @Test
    fun getUserById_NotNullTest() {
        val login = "login"
        Mockito.`when`(repository.getUserById(login)).thenReturn(Single.just(gitHubUser))
        val user = repository.getUserById(login).subscribeOn(Schedulers.io()).blockingGet()
        assertNotNull(user)
    }

    @Test
    fun getUserById_EqualsTest() {
        val login = "login"
        Mockito.`when`(repository.getUserById(login)).thenReturn(Single.just(gitHubUser))
        val user = repository.getUserById(login).subscribeOn(Schedulers.io()).blockingGet()
        assertEquals(user, gitHubUser)
    }

    @Test
    fun getUserRepos_NotNullTest() {
        val login = "login"
        Mockito.`when`(repository.getUserRepos(login)).thenReturn(Single.just(repoResponse))
        val repoList = repository.getUserRepos(login).subscribeOn(Schedulers.io()).blockingGet()
        assertNotNull(repoList)
    }

    @Test
    fun getUserRepos_NotEmptyListTest() {
        val login = "login"
        Mockito.`when`(repository.getUserRepos(login)).thenReturn(Single.just(repoResponse))
        val repoList = repository.getUserRepos(login).subscribeOn(Schedulers.io()).blockingGet()
        assertFalse(repoList.isEmpty())
    }

    @Test
    fun getUserRepos_EmptyListTest() {
        val login = "login"
        Mockito.`when`(repository.getUserRepos(login)).thenReturn(Single.just(emptyRepoResponse))
        val repoList = repository.getUserRepos(login).subscribeOn(Schedulers.io()).blockingGet()
        assertTrue(repoList.isEmpty())
    }
}