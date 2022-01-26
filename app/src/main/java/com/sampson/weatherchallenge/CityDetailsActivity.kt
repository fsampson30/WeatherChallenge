package com.sampson.weatherchallenge

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.GsonBuilder
import com.sampson.weatherchallenge.model.City
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_item_details)

        val pbCityDetails = findViewById<ProgressBar>(R.id.pbCityDetails)
        pbCityDetails.visibility = View.GONE

        val txtCityName = findViewById<TextView>(R.id.txtCityNameDetails)
        val txtMainWeather = findViewById<TextView>(R.id.txtCityMainWeatherDetails)
        val txtMainDescription = findViewById<TextView>(R.id.txtCityMainDescriptionDetails)
        val txtCityTemp = findViewById<TextView>(R.id.txtCityTemperatureDetails)
        val txtCityFeelsLike = findViewById<TextView>(R.id.txtICityFeelsLikeDetails)
        val txtCityMinTemp = findViewById<TextView>(R.id.txtCityMinTempDetails)
        val txtCityMaxTemp = findViewById<TextView>(R.id.txtCityMaxTempDetails)

        val cityId = intent.getSerializableExtra("cityId") as String

        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        pbCityDetails.visibility = View.VISIBLE
        apiService.getCityWeather(cityId, "metric", "141c2d43756d5d338b8ecf71bd892e02")
            .enqueue(object : Callback<City> {
                override fun onResponse(
                    call: Call<City>,
                    response: Response<City>
                ) {
                    Log.i("TAG", "onResponse ${response.body()}")
                    val cityWeather = response.body()
                    if (cityWeather != null) {
                        txtCityName.text = cityWeather.name
                        txtMainWeather.text = cityWeather.weather[0].mainWeather
                        txtMainDescription.text = cityWeather.weather[0].description
                        txtCityTemp.text = cityWeather.main.temp
                        txtCityFeelsLike.text = cityWeather.main.feels_like
                        txtCityMinTemp.text = cityWeather.main.temp_min
                        txtCityMaxTemp.text = cityWeather.main.temp_max
                        pbCityDetails.visibility = View.GONE
                    }
                    if (cityWeather == null) {
                        Log.w("TAG", "Did not receive a valid response body")
                        return
                    }
                }

                override fun onFailure(call: Call<City>, t: Throwable) {
                    Log.e("TAG", "onFailure $t")
                    pbCityDetails.visibility = View.GONE
                }
            })
    }
}