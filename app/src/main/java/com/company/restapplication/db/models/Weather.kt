package com.company.restapplication.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather (
    @PrimaryKey
    val id: Long,
    val city_name: String,
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val wind: Double
) {
    constructor(weather: Weather) : this(
        weather.id,
        weather.city_name,
        weather.temp,
        weather.pressure,
        weather.humidity,
        weather.wind
    )
}