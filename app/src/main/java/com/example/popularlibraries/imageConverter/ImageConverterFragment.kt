package com.example.popularlibraries.imageConverter

import android.Manifest
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.popularlibraries.R
import com.example.popularlibraries.databinding.ImageConverterLayoutBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

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
        ImageConverterPresenter(ImageConverterRepositoryImpl())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            100
        )
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

            convertButton.setOnClickListener {
                if (converterIv.drawable != null) {
                    val bitmap = (converterIv.drawable as BitmapDrawable).bitmap
                    presenter.convertImageButtonClick(bitmap)
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.picture_is_not_avalible),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun openFileChooser() {
        fileChooserContract.launch("image/*")
    }

    override fun pickImage() {
        openFileChooser()
    }

    override fun convertImage() {
        Toast.makeText(requireContext(), "converted", Toast.LENGTH_SHORT).show()
    }
}