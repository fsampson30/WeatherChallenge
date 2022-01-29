package com.sampson.weatherchallenge.model

import java.util.*

object Country {

    fun getCountryName(country: String) : String {
        return Locale("en_US",country).displayCountry
    }

    fun getCountryDefault(): String {
        return Locale.getDefault().displayCountry
    }
}