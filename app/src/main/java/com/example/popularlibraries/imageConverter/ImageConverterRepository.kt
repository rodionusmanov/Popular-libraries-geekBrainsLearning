package com.example.popularlibraries.imageConverter

import android.graphics.drawable.BitmapDrawable

interface ImageConverterRepository {

    fun getImage(): BitmapDrawable
}