package com.sampson.weatherchallenge.DAO

import androidx.annotation.WorkerThread

class CityRepository(private val cityDao: CityDao) {

    val allCities: kotlinx.coroutines.flow.Flow<MutableList<EntityCity>> = cityDao.getAll()

}