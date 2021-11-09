package com.company.restapplication.repo

import android.util.Log
import com.company.restapplication.db.models.Weather
import com.company.restapplication.rest.RestManager
import com.company.restapplication.rest.services.WeatherService
import com.company.restapplication.utils.RestUrls
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class WeatherRepoRemote @Inject constructor(
    private val localRepo: WeatherRepoLocal,
    restManager: RestManager
) : WeatherRepoInterface {

    private val service =
        restManager.createService(RestUrls.WEATHER_URL, WeatherService::class.java)

    override suspend fun getWeather(name: String): List<Weather> {
        return try {
            withContext(Dispatchers.IO) {
                val weather = service.getWeather(
                    if (name.isEmpty())
                        "1526273"
                    else
                        name
                )

                val weatherList = weather.data.results.map {
                    Weather(
                        it.id,
                        it.city_name,
                        it.temp,
                        it.pressure,
                        it.humidity,
                        it.wind
                    )
                }

                weatherList.forEach {
                    localRepo.addWeather(it)
                }


                return@withContext weatherList
            }
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "")
            return listOf()
        }
    }
}