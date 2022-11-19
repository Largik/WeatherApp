package com.lagrik.weatherapp.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hour(
    @Json(name = "time")
    val time: String? = null,
    @Json(name = "condition")
    val condition: Condition? = null,
    @Json(name = "temp_c")
    val temp_c: String? = null,
)
