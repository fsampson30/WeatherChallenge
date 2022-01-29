package com.sampson.weatherchallenge.model

import java.util.*
import kotlin.jvm.Throws

object Country {

    @Throws(InvalidStringException::class)
    fun getCountryName(country: String) : String {
        if (country.length != 2) {
            throw InvalidStringException
        }
        return Locale("en_US",country).displayCountry
    }

    fun getCountryDefault(): String {
        return Locale.getDefault().displayCountry
    }

    object InvalidStringException : Throwable()
}