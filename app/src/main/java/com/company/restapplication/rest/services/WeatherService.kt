package com.company.restapplication.rest.services

import com.company.restapplication.BuildConfig
import com.company.restapplication.rest.models.GetWeatherRest
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun getWeather(
        @Query("id") id: String?,
        @Query("lang") lang: String = "ru",
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = BuildConfig.API_WEATHER
    ): GetWeatherRest
}