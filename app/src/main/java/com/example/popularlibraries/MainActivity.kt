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
                presenter.onCounterClick(R.id.primary_button)
            }

            secondaryButton.setOnClickListener {
                presenter.onCounterClick(R.id.secondary_button)
            }

            lowButton.setOnClickListener {
                presenter.onCounterClick(R.id.low_button)
            }
        }
    }

    private fun initPresenter() {
        presenter = CountersPresenter(this)
    }

    override fun setText(counters: String, position: Int) {
        with(binding) {
            when (position) {
                0 -> primaryTV.text = counters
                1 -> secondaryTV.text = counters
                2 -> lowTV.text = counters
            }
        }
    }
}