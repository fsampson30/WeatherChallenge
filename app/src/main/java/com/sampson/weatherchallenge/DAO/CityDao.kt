package com.sampson.weatherchallenge.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sampson.weatherchallenge.model.PopulateCityList
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query("SELECT * FROM city_table")
    fun getAll(): Flow<MutableList<EntityCity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(cities : MutableList<EntityCity> = PopulateCityList.populateCitiesDatabase())

    @Query("DELETE FROM city_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM city_partials ORDER BY id DESC")
    fun getAllPartials() : Flow<MutableList<EntityCityPartials>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPartial(partial: EntityCityPartials)

}