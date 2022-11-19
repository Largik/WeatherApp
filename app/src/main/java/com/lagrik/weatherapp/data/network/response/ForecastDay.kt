package com.lagrik.weatherapp.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDay(
    @Json(name = "date")
    val date: String? = null,
    @Json(name = "day")
    val day: Day? = null,
    @Json(name = "hour")
    val hours: List<Hour>? = emptyList(),
)
