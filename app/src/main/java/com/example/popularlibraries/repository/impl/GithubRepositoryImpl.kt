package com.example.popularlibraries.repository.impl

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

class GithubRepositoryImpl constructor(
    private val usersApi: UsersApi,
    private val userDao: UserDAO,
    private val networkStatus: Single<Boolean>
) : GithubRepository, IUserCache {

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
                userDao.insertAll(it.map(UserMapper::mapToDBObject))
            }
            .map { it.map(UserMapper::mapToEntity) }
    }

    private fun getFromDb(): Single<List<GithubUser>> {
        return userDao.queryForAllUsers().map { it.map(UserMapper::mapToEntity) }
    }

    override fun getUserWithRepos(login: String): Single<GithubUser> {
        return userDao.getUsersWithRepos(login)
            .map { userWithRepos ->
                val user = UserMapper.mapToEntity(userWithRepos.userDBObject)
                user.repos = userWithRepos.repos.map { UserMapper.mapToEntityRepos(it) }
                user
            }
    }

    override fun getUserById(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(UserMapper::mapToEntity)
    }

    override fun getUserRepos(login: String): Single<List<UserRepo>> {
        return usersApi.getRepos(login)
            .map { it.map(UserMapper::mapToEntityRepos) }
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