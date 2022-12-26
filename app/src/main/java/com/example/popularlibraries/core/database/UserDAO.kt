package com.example.popularlibraries.core.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(userDBObject: UserDBObject): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(userDBObjects: List<UserDBObject>): Completable

    @Query("SELECT * FROM users")
    abstract fun queryForAllUsers(): Single<List<UserDBObject>>

    @Delete
    abstract fun delete(userDBObject: UserDBObject): Completable

    @Transaction
    @Query("SELECT * FROM users WHERE login = :login")
    abstract fun getUsersWithRepos(login: String): Single<UserWithReposDBObject>
}