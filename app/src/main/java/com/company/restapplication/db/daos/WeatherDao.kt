package com.company.restapplication.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.company.restapplication.db.models.Weather

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    suspend fun getWeather(): List<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWeather(item: Weather)

}