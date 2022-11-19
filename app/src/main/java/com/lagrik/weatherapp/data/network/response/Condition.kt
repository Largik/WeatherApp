package com.lagrik.weatherapp.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Condition(
    @Json(name = "text")
    val text: String? = null,
    @Json(name = "icon")
    val icon: String? = null,
)