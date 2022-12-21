package com.example.popularlibraries.imageConverter

import android.graphics.Bitmap
import android.os.Environment
import com.example.popularlibraries.core.utils.fakeDelay
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit


class ImageConverterPresenter(
    private val repository: ImageConverterRepositoryImpl
) : MvpPresenter<ImageConverterView>() {

    fun buttonPickClick() {
        repository.pickImage()
            .subscribeOn(Schedulers.newThread())
            .subscribe(
                {
                    viewState.pickImage()
                }, {}
            )
    }

    fun convertImageButtonClick(bitmap: Bitmap?) {
        repository.convertImage()
            .subscribeOn(Schedulers.newThread())
            .delay(fakeDelay.toLong(), TimeUnit.SECONDS)
            .subscribe(
                {
                    convertFile(bitmap)
                }, {}
            ).dispose()
        viewState.convertImage()
    }

    private fun convertFile(bitmap: Bitmap?) {
        val file =
            File(Environment.getExternalStorageDirectory().toString() + "/Pictures/converted.png")

        try {
            file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        var outputStream: FileOutputStream? = null

        try {
            outputStream = FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

        try {
            outputStream?.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        try {
            outputStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}