package com.lagrik.weatherapp.domain.repository

import com.lagrik.weatherapp.domain.model.WeatherModel

interface ForecastRepository {
    suspend fun getWeatherData(city: String)
    suspend fun getForecastList(city: String): List<WeatherModel>
    suspend fun getCurrentDay(): WeatherModel
}