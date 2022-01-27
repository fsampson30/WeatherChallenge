package com.sampson.weatherchallenge.controller

import com.sampson.weatherchallenge.model.City
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather?")
    fun getCityWeatherById(
        @Query("id") id: String,
        @Query("units") units: String,
        @Query("appid") key: String
    ): Call<City>

    @GET("data/2.5/weather?")
    fun getCityWeatherByCoordinates(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String,
        @Query("appid") key: String
    ): Call<City>
}