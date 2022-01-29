package com.sampson.weatherchallenge.DAO

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CitiesApplication() : Application(){

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { CityRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { CityRepository(database.CityDao()) }
}