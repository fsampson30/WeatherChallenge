package com.sampson.weatherchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.sampson.weatherchallenge.R
import com.sampson.weatherchallenge.controller.ApiService
import com.sampson.weatherchallenge.controller.Constants.APIKEY
import com.sampson.weatherchallenge.controller.Constants.UNITS
import com.sampson.weatherchallenge.controller.Constants.BASE_URL
import com.sampson.weatherchallenge.controller.Constants.IMAGE_URL
import com.sampson.weatherchallenge.model.City
import com.sampson.weatherchallenge.model.Country.getCountryDefault
import com.sampson.weatherchallenge.model.Country.getCountryName
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityDetailsActivityByCoordinates : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_details_by_coordinates)

        val txtCityName = findViewById<TextView>(R.id.txtCityNameDetailsCoordinates)
        val txtMainWeather = findViewById<TextView>(R.id.txtCityMainWeatherDetailsCoordinates)
        val txtMainDescription = findViewById<TextView>(R.id.txtCityMainDescriptionDetailsCoordinates)
        val txtCityTemp = findViewById<TextView>(R.id.txtCityTemperatureDetailsCoordinates)
        val txtCityFeelsLike = findViewById<TextView>(R.id.txtICityFeelsLikeDetailsCoordinates)
        val txtCityMinTemp = findViewById<TextView>(R.id.txtCityMinTempDetailsCoordinates)
        val txtCityMaxTemp = findViewById<TextView>(R.id.txtCityMaxTempDetailsCoordinates)
        val txtCityCountry = findViewById<TextView>(R.id.txtCityCountryDetailsCoordinates)
        val txtCityLatitude = findViewById<TextView>(R.id.txtCityLatitudeDetailsCoordinates)
        val txtCityLongitude = findViewById<TextView>(R.id.txtCityLongitudeDetailsCoordinates)
        val imgWeather = findViewById<ImageView>(R.id.imgPictureWeatherDetailsCoordinates)
        val pbCityDetails = findViewById<ProgressBar>(R.id.pbCityDetailsCoordinates)

        pbCityDetails.visibility = View.GONE

        val latitude = intent.getSerializableExtra("latitude") as String
        val longitude = intent.getSerializableExtra("longitude") as String
        Log.d("TAG",latitude)
        Log.d("TAG",longitude)

        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        pbCityDetails.visibility = View.VISIBLE
        apiService.getCityWeatherByCoordinates(latitude,longitude, UNITS, APIKEY)
            .enqueue(object : Callback<City> {
                override fun onResponse(
                    call: Call<City>,
                    response: Response<City>
                ) {
                    Log.i("TAG", "onResponse ${response.body()}")
                    val cityWeather = response.body()
                    if (cityWeather == null) {
                        Log.w("TAG", "Did not receive a valid response body")
                        Log.w("TAG", getCountryDefault())
                        Toast.makeText(baseContext, getString(R.string.error_api), Toast.LENGTH_LONG).show()
                        pbCityDetails.visibility = View.GONE
                        onBackPressed()
                        return
                    }
                    if (response.body() != null) {
                        pbCityDetails.visibility = View.VISIBLE
                        txtCityName.text = cityWeather.name
                        txtMainWeather.text = cityWeather.weather[0].mainWeather
                        txtMainDescription.text = cityWeather.weather[0].description
                        val icon = cityWeather.weather[0].icon + "@2x.png"
                        (getString(R.string.city_temp) + cityWeather.main.temp + getString(R.string.temp_signal)).also {
                            txtCityTemp.text = it
                        }
                        (getString(R.string.feels_like) + cityWeather.main.feels_like + getString(R.string.temp_signal)).also {
                            txtCityFeelsLike.text = it
                        }
                        (getString(R.string.city_min) + cityWeather.main.temp_min + getString(R.string.temp_signal)).also {
                            txtCityMinTemp.text = it
                        }
                        (getString(R.string.city_max) + cityWeather.main.temp_max + getString(R.string.temp_signal)).also {
                            txtCityMaxTemp.text = it
                        }
                        (getString(R.string.city_country) + getCountryName(cityWeather.sys.country)).also { txtCityCountry.text = it }
                        txtCityLatitude.text = latitude
                        txtCityLongitude.text = longitude
                        Picasso.get().load(IMAGE_URL + icon).into(imgWeather)
                        pbCityDetails.visibility = View.GONE

                    }

                }

                override fun onFailure(call: Call<City>, t: Throwable) {
                    Log.e("TAG", "onFailure $t")
                }

            })
    }
}