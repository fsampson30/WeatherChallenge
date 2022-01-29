package com.sampson.weatherchallenge.DAO

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "city_table")
class EntityCity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var cityName: String = "",
    var cityCode: String = ""

) : Serializable