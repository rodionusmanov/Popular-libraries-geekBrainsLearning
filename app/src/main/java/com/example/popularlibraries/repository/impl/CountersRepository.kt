package com.example.popularlibraries.repository.impl

class CountersRepository {

    private var counters = mutableListOf(0, 0, 0)

    fun getCurrent(position: Int): Int {
        return counters[position]
    }

    fun getAll(): IntArray {
        return counters.toIntArray()
    }

    fun setAll(array: IntArray) {
        counters = array.toMutableList()
    }

    fun next(position: Int): Int {
        return ++counters[position]
    }

    fun set(position: Int, value: Int) {
        counters[position] = value
    }
}