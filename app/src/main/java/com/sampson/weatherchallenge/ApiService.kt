package com.sampson.weatherchallenge

import com.sampson.weatherchallenge.model.City
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("data/2.5/weather?")
    fun getCityWeather(@Query("id") id: String, @Query("units") units : String ,@Query("appid") key : String): Call<City>
}