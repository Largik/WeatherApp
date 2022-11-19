package com.lagrik.weatherapp.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Day(
    @Json(name = "avgtemp_c")
    val avgTemp: String,
    @Json(name = "maxtemp_c")
    val maxTemp: String,
    @Json(name = "mintemp_c")
    val minTemp: String,
    @Json(name = "avghumidity")
    val humidity: String,
    @Json(name = "maxwind_kph")
    val wind_kph: String,
    @Json(name = "condition")
    val condition: Condition? = null,
)
