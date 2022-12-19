package com.example.popularlibraries.imageConverter

import io.reactivex.rxjava3.core.Single

class ImageConverterRepositoryImpl {

    fun pickImage(): Single<Any> {
        return Single.create {
            it.onSuccess(Any())
        }
    }

    fun convertImage(): Single<Any>{
        return Single.create {
            it.onSuccess(Any())
        }
    }
}