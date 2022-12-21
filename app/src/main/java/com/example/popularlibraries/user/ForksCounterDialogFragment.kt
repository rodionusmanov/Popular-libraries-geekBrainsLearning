package com.example.popularlibraries.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.popularlibraries.databinding.ForksCountDialogBinding

class ForksCounterDialogFragment(private val forksCount: Int) : DialogFragment() {

    private lateinit var viewBinding: ForksCountDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ForksCountDialogBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(viewBinding) {
            forksCountTv.text = "Number of forks: $forksCount"
            root.setOnClickListener {
                dismiss()
            }
        }
    }
}