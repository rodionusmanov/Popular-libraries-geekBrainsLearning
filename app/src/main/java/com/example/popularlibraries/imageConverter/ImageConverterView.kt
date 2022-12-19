package com.example.popularlibraries.imageConverter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ImageConverterView: MvpView {

    fun pickImage()

    fun convertImage()
}