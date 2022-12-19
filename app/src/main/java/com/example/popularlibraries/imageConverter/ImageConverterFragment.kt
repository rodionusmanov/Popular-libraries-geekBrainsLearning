package com.example.popularlibraries.imageConverter

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.popularlibraries.PopularLibrariesApp
import com.example.popularlibraries.databinding.ImageConverterLayoutBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter

class ImageConverterFragment : MvpAppCompatFragment(), ImageConverterView {

    companion object {
        fun getInstance(): ImageConverterFragment {
            return ImageConverterFragment()
        }
    }

    private lateinit var binding: ImageConverterLayoutBinding

    private val fileChooserContract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
            if (imageUri != null) {
                binding.converterIv.setImageURI(imageUri)
            }
        }

    private val presenter: ImageConverterPresenter by moxyPresenter {
        ImageConverterPresenter(ImageConverterRepositoryImpl(), PopularLibrariesApp.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ImageConverterLayoutBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            pickImageButton.setOnClickListener {
                presenter.buttonPickClick()
            }

            /*convertButton.setOnClickListener {
                if (converterIv.drawable != null) {
                    val bitmap = (converterIv.getDrawable() as BitmapDrawable).bitmap
                    Toast.makeText(requireContext(), bitmap.toString(), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "нет изображения для конвертации", Toast.LENGTH_SHORT).show()
                }
            }*/
        }
    }

    fun openFileChooser() {
        fileChooserContract.launch("image/*")
    }

    override fun pickImage() {
        openFileChooser()
    }
}