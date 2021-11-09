package com.company.restapplication.model

data class Weather(
    val city_name: String,
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val wind: Double
)