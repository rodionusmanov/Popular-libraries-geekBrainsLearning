package com.example.popularlibraries

class CountersPresenter(private val view: MainView) {

    val model = CountersModel()

    fun onCounterClick(id: Int) {
        when (id) {
            0 -> {
                view.setText(model.next(0).toString(), 0)
            }
            1 -> {
                view.setText(model.next(1).toString(), 1)
            }
            2 -> {
                view.setText(model.next(2).toString(), 2)
            }
        }
    }

    fun saveInstanceState(): IntArray {
        return model.getAll()
    }

    fun restoreInstanceState(counters: IntArray){
        model.setAll(counters)
    }
}