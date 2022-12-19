package com.example.popularlibraries.imageConverter

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import io.reactivex.rxjava3.core.Single

class ImageConverterRepositoryImpl {

    fun pickImage(): Single<Any> {
        return Single.create {
            it.onSuccess(Any())
        }
    }
}