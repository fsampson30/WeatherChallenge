package com.sampson.weatherchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.sampson.weatherchallenge.DAO.CitiesApplication
import com.sampson.weatherchallenge.DAO.EntityCityPartials
import com.sampson.weatherchallenge.R
import com.sampson.weatherchallenge.controller.ApiService
import com.sampson.weatherchallenge.controller.CityDetailsAdapter
import com.sampson.weatherchallenge.model.City
import com.sampson.weatherchallenge.controller.Constants.APIKEY
import com.sampson.weatherchallenge.controller.Constants.BASE_URL
import com.sampson.weatherchallenge.controller.Constants.IMAGE_URL
import com.sampson.weatherchallenge.controller.Constants.UNITS
import com.sampson.weatherchallenge.controller.CurrentTimeStamp.getCurrentTimeStamp
import com.sampson.weatherchallenge.model.Country
import com.sampson.weatherchallenge.viewModel.CitiWeatherViewModelFactory
import com.sampson.weatherchallenge.viewModel.CityWeatherViewModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityDetailsActivity : AppCompatActivity() {

    private val cityWeatherViewModel: CityWeatherViewModel by viewModels {
        CitiWeatherViewModelFactory((application as CitiesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_item_details)

        val txtCityName = findViewById<TextView>(R.id.txtCityNameDetails)
        val txtMainWeather = findViewById<TextView>(R.id.txtCityMainWeatherDetails)
        val txtMainDescription = findViewById<TextView>(R.id.txtCityMainDescriptionDetails)
        val txtCityTemp = findViewById<TextView>(R.id.txtCityTemperatureDetails)
        val txtCityFeelsLike = findViewById<TextView>(R.id.txtICityFeelsLikeDetails)
        val txtCityMinTemp = findViewById<TextView>(R.id.txtCityMinTempDetails)
        val txtCityMaxTemp = findViewById<TextView>(R.id.txtCityMaxTempDetails)
        val txtCityCountry = findViewById<TextView>(R.id.txtCityCountryDetails)
        val imgWeather = findViewById<ImageView>(R.id.imgPictureWeatherDetails)
        val rvPartials = findViewById<RecyclerView>(R.id.rvCityDetailsPartials)
        val pbCityDetails = findViewById<ProgressBar>(R.id.pbCityDetails)

        val adapter = CityDetailsAdapter(baseContext)
        rvPartials.layoutManager = LinearLayoutManager(baseContext)
        rvPartials.adapter = adapter

        pbCityDetails.visibility = View.GONE
        val cityId = intent.getSerializableExtra("cityId") as String

        cityWeatherViewModel.allPartials.observe(this) { partials ->
            partials.let { adapter.submitList(it) }
        }


        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        pbCityDetails.visibility = View.VISIBLE
        apiService.getCityWeatherById(cityId, UNITS, APIKEY)
            .enqueue(object : Callback<City> {
                override fun onResponse(
                    call: Call<City>,
                    response: Response<City>
                ) {
                    Log.i("TAG", "onResponse ${response.body()}")
                    val cityWeather = response.body()
                    if (cityWeather == null) {
                        Log.w("TAG", "Did not receive a valid response body")
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
                        (getString(R.string.city_country) + Country.getCountryName(cityWeather.sys.country)).also {
                            txtCityCountry.text = it
                        }

                        Picasso.get().load(IMAGE_URL + icon).into(imgWeather)
                        pbCityDetails.visibility = View.GONE

                        cityWeatherViewModel.insert(
                            EntityCityPartials(
                                0,
                                txtCityName.text.toString(),
                                cityId,
                                txtCityTemp.text.toString(),
                                txtMainWeather.text.toString(),
                                getCurrentTimeStamp()
                            )
                        )
                        cityWeatherViewModel.allPartials.observe(this@CityDetailsActivity) { partials ->
                            partials.let { adapter.submitList(it.filter { key-> key.cityCode == cityId }.toMutableList()) }
                        }

                    }

                }

                override fun onFailure(call: Call<City>, t: Throwable) {
                    Log.e("TAG", "onFailure $t")
                }

            })
    }
}