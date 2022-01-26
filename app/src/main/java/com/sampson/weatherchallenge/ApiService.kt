package com.sampson.weatherchallenge

import com.sampson.weatherchallenge.model.City
import retrofit2.Call
import retrofit2.http.GET

interface ApiService{

    @GET("api.openweathermap.org/data/2.5/weather?id={id}}&units=metric&appid=")
    fun getCityWeather(): Call<City>
}