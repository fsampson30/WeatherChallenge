package com.sampson.weatherchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sampson.weatherchallenge.controller.CityAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvCityList = findViewById<RecyclerView>(R.id.rvCityListMainActivity)

        val adapter = CityAdapter(baseContext)
        rvCityList.layoutManager = LinearLayoutManager(baseContext)
        rvCityList.adapter = adapter

    }
}