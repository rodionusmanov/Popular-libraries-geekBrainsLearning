package com.example.popularlibraries.imageConverter

import com.example.popularlibraries.core.navigation.ImageConverterScreen
import com.example.popularlibraries.utils.fakeDelay
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class ImageConverterPresenter(
    private val repository: ImageConverterRepositoryImpl,
    private val router: Router
) : MvpPresenter<ImageConverterView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun buttonPickClick() {
        repository.pickImage()
            .subscribeOn(Schedulers.io())
            .delay(fakeDelay.toLong(), TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.pickImage()
                }, {}
            )
    }
}