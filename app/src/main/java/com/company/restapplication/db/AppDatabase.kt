package com.company.restapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.company.restapplication.db.daos.WeatherDao
import com.company.restapplication.db.models.Weather

@Database(
    entities = [
        Weather::class
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}