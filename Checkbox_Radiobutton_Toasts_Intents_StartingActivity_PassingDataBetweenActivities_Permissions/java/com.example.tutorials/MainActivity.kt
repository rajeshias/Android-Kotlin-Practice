package com.example.tutorials

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var burger = Burger("", false)
        btnOrder.setOnClickListener {
            val burgerType = rgBurger.checkedRadioButtonId
            val meat = findViewById<RadioButton>(burgerType).text.toString()
            val extras = "${if (cbCheese.isChecked) "Cheese\n" else ""}${if (cbOnions.isChecked) "Onion\n" else ""}${if (cbSalad.isChecked) "Salad" else ""}"
            burger = Burger(meat, cbSalad.isChecked)
            tvResult.setText("You ordered a $meat Burger with extras:\n$extras")
        }

        btnToast.setOnClickListener {
            Toast.makeText(this,"This is a toast", Toast.LENGTH_LONG).show()
        }

        btnNext.setOnClickListener {
            Intent(this, SecondActivity::class.java).also {
                it.putExtra("EXTRA_BURGER", burger)
                startActivity(it)
            }
        }

        btnRequestPermissions.setOnClickListener {
            requestPermissions()
        }

    }

    private fun hasWriteExternalStorage() = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    private fun hasForegroundLocationPermission() = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    private fun hasBackgroundLocationPermission() = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions() {
        var permissionsRequests = mutableListOf<String>()
        if(!hasWriteExternalStorage()){
            permissionsRequests.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!hasForegroundLocationPermission()) {
            permissionsRequests.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        // BackgroundLocation only gor Android API < 30
//        if(!hasBackgroundLocationPermission()){
//            permissionsRequests.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
//        }
        if(permissionsRequests.isNotEmpty()){
            Log.d("permissionsRequests", permissionsRequests.toString())
            ActivityCompat.requestPermissions(this, permissionsRequests.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0 && grantResults.isNotEmpty()){
            for(i in grantResults.indices){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("permissionsGranted", "${permissions[i]} has been granted.")
                }
            }
        }
    }
}
