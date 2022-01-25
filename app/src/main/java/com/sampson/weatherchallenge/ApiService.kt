package com.sampson.weatherchallenge

import com.sampson.weatherchallenge.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface ApiService{

    @GET("api.openweathermap.org/data/2.5/weather?id={id}}&units=metric&appid=141c2d43756d5d338b8ecf71bd892e02")
    fun getCityWeather(): Call<Country>
}