package com.company.restapplication.repo

import com.company.restapplication.db.AppDatabase
import com.company.restapplication.db.models.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class WeatherRepoLocal @Inject constructor(
    private val db: AppDatabase
) : WeatherRepoInterface {

    override suspend fun getWeather(name: String): List<Weather> {
        return try {
            withContext(Dispatchers.IO) {
                val items = db.weatherDao().getWeather()
                return@withContext items
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    suspend fun addWeather(item: Weather) {
        db.weatherDao().addWeather(item)
    }
}