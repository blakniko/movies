package com.gasto.movies.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gasto.movies.R


class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyPermission()
    }

    private fun verifyPermission() {

        val perms = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val accessFinePermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        val accessCoarsePermission = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        val cameraPermission = checkSelfPermission(Manifest.permission.CAMERA)
        val storagePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (cameraPermission != PackageManager.PERMISSION_GRANTED && accessFinePermission != PackageManager.PERMISSION_GRANTED && accessCoarsePermission != PackageManager.PERMISSION_GRANTED && storagePermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(perms, REQUEST_CODE)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            100 -> {

                for (element in permissions) {

                    if (checkSelfPermission(element) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(arrayOf(element), 100)
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}