package com.sampson.weatherchallenge.controller

import java.text.SimpleDateFormat
import java.util.*

object CurrentTimeStamp {

    fun getCurrentTimeStamp(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy HH:mm:ss")
        return sdf.format(Date())
    }
}