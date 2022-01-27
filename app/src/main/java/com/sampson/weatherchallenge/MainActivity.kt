package com.sampson.weatherchallenge

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.sampson.weatherchallenge.controller.CityAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvCityList = findViewById<RecyclerView>(R.id.rvCityListMainActivity)
        val btnCurrentLocation = findViewById<Button>(R.id.btnCurrentLocationMainActivity)

        val adapter = CityAdapter(baseContext)
        rvCityList.layoutManager = LinearLayoutManager(baseContext)
        rvCityList.adapter = adapter

        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)
        var latitude = ""
        var longitude = ""

        if (checkPermission()) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                latitude = location?.latitude.toString()
                longitude = location?.longitude.toString()
            }
        }

        btnCurrentLocation.setOnClickListener {
            val intent = Intent(baseContext, CityDetailsActivityByCoordinates::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("latitude",latitude )
            intent.putExtra("longitude", longitude)
            baseContext.startActivity(intent)
        }

    }

    private fun checkPermission(): Boolean {
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
            return false
        }
    }

    private fun getCurrentLocation(){

    }
}
