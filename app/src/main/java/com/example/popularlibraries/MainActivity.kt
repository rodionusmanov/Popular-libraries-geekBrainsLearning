package com.example.popularlibraries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.popularlibraries.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: CountersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPresenter()

        with(binding) {
            primaryButton.setOnClickListener {
                presenter.onCounterClick(0)
            }

            secondaryButton.setOnClickListener {
                presenter.onCounterClick(1)
            }

            lowButton.setOnClickListener {
                presenter.onCounterClick(2)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("counters", presenter.saveInstanceState())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoredArray = savedInstanceState.getIntArray("counters")
        restoredArray?.let {
            presenter.restoreInstanceState(restoredArray)
            for (i in restoredArray.indices){
                setText(restoredArray[i].toString(), i)
            }
        }
    }

    private fun initPresenter() {
        presenter = CountersPresenter(this)
    }

    override fun setText(counter: String, position: Int) {
        with(binding) {
            when (position) {
                0 -> primaryTV.text = counter
                1 -> secondaryTV.text = counter
                2 -> lowTV.text = counter
            }
        }
    }
}