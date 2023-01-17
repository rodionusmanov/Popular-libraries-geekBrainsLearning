package com.example.popularlibraries.repository.impl

import com.example.popularlibraries.PopularLibrariesApp
import com.example.popularlibraries.core.database.UserDAO
import com.example.popularlibraries.core.database.caches.IUserCache
import com.example.popularlibraries.core.mapper.UserMapper
import com.example.popularlibraries.core.network.UsersApi
import com.example.popularlibraries.core.utils.sourceFlag
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.UserRepo
import com.example.popularlibraries.repository.GithubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GithubRepositoryImpl constructor(
    private val usersApi: UsersApi,
    private val userDao: UserDAO,
    private val networkStatus: Single<Boolean>
) : GithubRepository, IUserCache {

    @Inject
    lateinit var mapper: UserMapper

    private val userMapper: UserMapper by lazy {
<<<<<<< HEAD
        PopularLibrariesApp.instance.applicationComponent.inject(this)
=======
        PopularLibrariesApp.applicationComponent.injectUserMapper(this)
>>>>>>> a09c566 (8 ДЗ. Добавил компонент для зависимости класса UserMapper.)
        mapper
    }

    override fun getUsers(): Single<List<GithubUser>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) {
                fetchFromApi(sourceFlag)
            } else {
                getFromDb()
            }
        }
    }

    private fun fetchFromApi(shouldPersist: Boolean): Single<List<GithubUser>> {
        return usersApi.getAllUsers()
            .doCompletableIf(shouldPersist) {
                userDao.insertAll(it.map(userMapper::mapToDBObject))
            }
            .map { it.map(userMapper::mapToEntity) }
    }

    private fun getFromDb(): Single<List<GithubUser>> {
        return userDao.queryForAllUsers().map { it.map(userMapper::mapToEntity) }
    }

    override fun getUserWithRepos(login: String): Single<GithubUser> {
        return userDao.getUsersWithRepos(login)
            .map { userWithRepos ->
                val user = userMapper.mapToEntity(userWithRepos.userDBObject)
                user.repos = userWithRepos.repos.map { userMapper.mapToEntityRepos(it) }
                user
            }
    }

    override fun getUserById(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(userMapper::mapToEntity)
    }

    override fun getUserRepos(login: String): Single<List<UserRepo>> {
        return usersApi.getRepos(login)
            .map { it.map(userMapper::mapToEntityRepos) }
    }

    override fun <T> Single<T>.doCompletableIf(
        predicate: Boolean,
        completableCreator: (data: T) -> Completable
    ): Single<T> {
        return if (predicate) {
            this.flatMap {
                completableCreator(it).andThen(Single.just(it))
            }
        } else {
            this
        }
    }
}