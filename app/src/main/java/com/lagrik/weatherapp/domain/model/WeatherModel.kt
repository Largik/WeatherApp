package com.lagrik.weatherapp.domain.model

data class WeatherModel(
    val city: String? = null,
    val submitLocation: String? = null,
    val date: String? = null,
    val condition: String? = null,
    val avgTemp: String? = null,
    val maxTemp: String? = null,
    val minTemp: String? = null,
    val humidity: String? = null,
    val wind_kph: String? = null,
    val imageUrl: String? = null,
    val hours: List<HourModel>,
)