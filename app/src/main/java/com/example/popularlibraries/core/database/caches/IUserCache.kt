package com.example.popularlibraries.core.database.caches

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IUserCache {
    fun <T> Single<T>.doCompletableIf(
        predicate: Boolean,
        completableCreator: (data: T) -> Completable
    ): Single<T>
}