package com.sampson.weatherchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        apiService.getCityWeather("2267057", "metric","").enqueue(object : Callback<City> {
            override fun onResponse(
                call: Call<City>,
                response: Response<City>
            ) {
                Log.i("TAG", "onResponse ${response.body()}")
                val nationalData = response.body()
                if (nationalData == null) {
                    Log.w("TAG", "Did not receive a valid response body")
                    return
                }
            }
            override fun onFailure(call: Call<City>, t: Throwable) {
                Log.e("TAG", "onFailure $t")
            }
        })
    }
}