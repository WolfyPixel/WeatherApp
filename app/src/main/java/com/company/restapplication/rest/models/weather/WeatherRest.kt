package com.company.restapplication.rest.models.weather

data class WeatherRest(
    val id: Long,
    val city_name: String,
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val wind: Double
)