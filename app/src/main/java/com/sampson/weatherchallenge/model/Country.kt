package com.sampson.weatherchallenge.model

import android.util.Log
import java.util.*

object Country {

    fun getCountryName(country: String) : String {
        return Locale("en_US",country).displayCountry

    }
}