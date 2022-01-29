package com.sampson.weatherchallenge.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sampson.weatherchallenge.model.PopulateCityList

@Dao
interface CityDao {
    @Query("SELECT * FROM city_table")
    fun getAll(): kotlinx.coroutines.flow.Flow<MutableList<EntityCity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(cities : MutableList<EntityCity> = PopulateCityList.populateCitiesDatabase())

    @Query("DELETE FROM city_table")
    suspend fun deleteAll()
}