package com.example.popularlibraries

class CountersPresenter(private val view: MainView) {

    val model = CountersModel()

    fun onCounterClick(id: Int) {
        when (id) {
            R.id.primary_button -> {
                val newValue = model.next(0)
                view.setText(newValue.toString(), 0)
            }
            R.id.secondary_button -> {
                val newValue = model.next(1)
                view.setText(newValue.toString(), 1)
            }
            R.id.low_button -> {
                val newValue = model.next(2)
                view.setText(newValue.toString(), 2)
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