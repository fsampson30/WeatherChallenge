package com.sampson.weatherchallenge.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(EntityCity::class), version = 1, exportSchema = false)
abstract class CityRoomDatabase: RoomDatabase() {

    abstract fun CityDao() : CityDao

    companion object{
        @Volatile
        private var INSTANCE: CityRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ) : CityRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,com.sampson.weatherchallenge.DAO.CityRoomDatabase::class.java,
                    "city_database"
                ).addCallback(CityDataBaseCallback(scope)).build()
                com.sampson.weatherchallenge.DAO.CityRoomDatabase.Companion.INSTANCE = instance
                instance
            }
        }
    }

    private class CityDataBaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.CityDao())
                }
            }
        }

        suspend fun populateDatabase(cityDao: CityDao){
            cityDao.insertAll()
        }
    }
}