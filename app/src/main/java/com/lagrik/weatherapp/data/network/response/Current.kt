package com.lagrik.weatherapp.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Current(
    @Json(name = "last_updated")
    val timeUpdate: String,
    @Json(name = "humidity")
    val humidity: String,
    @Json(name = "temp_c")
    val tempC: String,
    @Json(name = "wind_kph")
    val wind_kph: String,
    @Json(name = "condition")
    val condition: Condition? = null,
)
