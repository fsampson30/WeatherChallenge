package com.sampson.weatherchallenge.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Country(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("main") val mainWeather: String,
    @SerializedName("description") val description : String,
    @SerializedName("feels_like") val tempSensation: Int,
    @SerializedName("temp_min") val tempMin: Int,
    @SerializedName("temp_max") val tempMax: Int
) : Serializable
