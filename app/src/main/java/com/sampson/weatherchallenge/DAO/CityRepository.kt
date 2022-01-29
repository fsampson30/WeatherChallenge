package com.sampson.weatherchallenge.DAO

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class CityRepository(private val cityDao: CityDao) {

    val allCities: Flow<MutableList<EntityCity>> = cityDao.getAll()
    val allPartials : Flow<MutableList<EntityCityPartials>> = cityDao.getAllPartials()

    @WorkerThread
    suspend fun insertPartial(partial: EntityCityPartials){
        cityDao.insertPartial(partial)
    }
}