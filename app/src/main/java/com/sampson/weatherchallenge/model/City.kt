package com.sampson.weatherchallenge.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class City(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("weather") val weather: ArrayList<Weather>,
    @SerializedName("main") val main: Main,
    @SerializedName("sys") val sys: Sys
) : Serializable

data class Weather(
    @SerializedName("main") val mainWeather: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String,
) : Serializable

data class Main(
    @SerializedName("temp") val temp: String,
    @SerializedName("feels_like") val feels_like: String,
    @SerializedName("temp_min") val temp_min: String,
    @SerializedName("temp_max") val temp_max: String
)

data class Sys(
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int
)
