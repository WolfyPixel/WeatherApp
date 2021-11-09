package com.company.restapplication.repo

import com.company.restapplication.db.models.Weather

interface WeatherRepoInterface {

    suspend fun getWeather(name: String) : List<Weather>
}