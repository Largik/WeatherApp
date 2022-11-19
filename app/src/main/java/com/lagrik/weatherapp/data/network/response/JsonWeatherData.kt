package com.lagrik.weatherapp.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonWeatherData(
    @Json(name = "location")
    val location: Location? = null,
    @Json(name = "current")
    val current: Current? = null,
    @Json(name = "forecast")
    val forecast: Forecast? = null,
)