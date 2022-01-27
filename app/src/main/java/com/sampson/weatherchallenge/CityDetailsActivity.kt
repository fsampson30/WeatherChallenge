package com.sampson.weatherchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.GsonBuilder
import com.sampson.weatherchallenge.model.City
import com.sampson.weatherchallenge.model.Constants.APIKEY
import com.sampson.weatherchallenge.model.Constants.BASE_URL
import com.sampson.weatherchallenge.model.Constants.IMAGE_URL
import com.squareup.picasso.Picasso
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

        val imgWeather = findViewById<ImageView>(R.id.imgPictureWeatherDetails)

        val cityId = intent.getSerializableExtra("cityId") as String

        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        pbCityDetails.visibility = View.VISIBLE
        apiService.getCityWeather(cityId, "metric", APIKEY)
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
                        val icon = cityWeather.weather[0].icon + "@2x.png"
                        (getString(R.string.city_temp) +  cityWeather.main.temp + getString(R.string.temp_signal)).also { txtCityTemp.text = it }
                        (getString(R.string.feels_like) +  cityWeather.main.feels_like + getString(R.string.temp_signal)).also { txtCityFeelsLike.text = it }
                        (getString(R.string.city_min) + cityWeather.main.temp_min + getString(R.string.temp_signal)).also { txtCityMinTemp.text = it }
                        (getString(R.string.city_max) + cityWeather.main.temp_max + getString(R.string.temp_signal)).also { txtCityMaxTemp.text = it }

                        Picasso.get().load(IMAGE_URL+icon).into(imgWeather)

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