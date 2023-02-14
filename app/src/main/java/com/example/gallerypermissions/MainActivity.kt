package com.example.gallerypermissions

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.gallerypermissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val CAMERA_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )) {
            requestExternalPermission()
        }
        else {
            requestExternalPermission()
        }
//        binding?.uiButtonCaptureImage?.setOnClickListener {
//            openCameraForImage()
//        }
        binding?.uiButtonGetImage?.setOnClickListener {
            openGalleryForImage()
        }
    }
//    private fun requestPermissionRationale():Boolean {
//        (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE))
//        return true
//    }

    private fun requestExternalPermission() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100){
            binding?.uiIvDisplayImage?.setImageURI(data?.data) // handle chosen image
        }
    }
}