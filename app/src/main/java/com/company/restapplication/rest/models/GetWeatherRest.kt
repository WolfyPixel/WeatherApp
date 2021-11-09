package com.company.restapplication.rest.models

import com.company.restapplication.rest.models.weather.WeatherRest

data class GetWeatherRest(
    val code: Int?,
    val status: String?,
    val data: DataContainer
) {
    data class DataContainer(
        val results: List<WeatherRest>
    )
}