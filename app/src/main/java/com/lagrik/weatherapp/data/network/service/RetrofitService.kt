package com.lagrik.weatherapp.data.network.service

import com.lagrik.weatherapp.data.network.Constant
import com.lagrik.weatherapp.data.network.response.JsonWeatherData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("forecast.json?&aqi=no&alerts=no")
    fun getForecastAsync(
        @Query("q") city: String,
        @Query("key") key: String = Constant.API_KEY,
        @Query("days") days: Int = Constant.COUNT_DAYS,
    ): Deferred<JsonWeatherData>
}