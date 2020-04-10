package com.example.topredditposts.ui.main

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.drawToBitmap
import com.example.topredditposts.databinding.ImageLayoutBinding
import com.example.topredditposts.ui.core.BaseActivity
import com.example.topredditposts.ui.core.BaseFragment
import java.io.File
import java.io.IOException


class ImageFragment(private val image: String) : BaseFragment() {

    companion object{
        const val REQUEST_STORAGE_PERMISSION = 1
    }

    override val layoutId: Int = com.example.topredditposts.R.layout.image_layout
    private lateinit var binding: ImageLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ImageLayoutBinding.inflate(inflater, container, false)
        binding.root.setOnClickListener {  }
        binding.imageUrl = image
        setupView(binding.root)
        return binding.root
    }

    override fun setupView(view: View) {
        super.setupView(view)
        binding.imageToolbar.imageSaveOption.setOnClickListener {
            base {
                if (ContextCompat.checkSelfPermission(
                        activity!!.applicationContext,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            REQUEST_STORAGE_PERMISSION
                        )
                    }
                } else {
                    addImageToGallery(binding.increasedImage);
                }

            }
        }
    }

    private fun addImageToGallery(it: ImageView) {
        val relativeLocation = Environment.DIRECTORY_PICTURES + File.pathSeparator + System.currentTimeMillis().toString()
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, System.currentTimeMillis().toString())
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, relativeLocation)
                put(MediaStore.MediaColumns.IS_PENDING, 1)
            }
        }

        val resolver = requireActivity().contentResolver
        val bitmap = it.drawToBitmap()

        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        try {

            uri?.let { uri ->
                val stream = resolver.openOutputStream(uri)

                stream?.let { stream ->
                    if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)) {
                        throw IOException("Failed to save bitmap.")
                    }
                } ?: throw IOException("Failed to get output stream.")

            } ?: throw IOException("Failed to create new MediaStore record")

        } catch (e: IOException) {
            if (uri != null) {
                resolver.delete(uri, null, null)
            }
            throw IOException(e)
        } finally {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                contentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_STORAGE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addImageToGallery(binding.increasedImage)
                }
                return
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        base { close() }
    }

}