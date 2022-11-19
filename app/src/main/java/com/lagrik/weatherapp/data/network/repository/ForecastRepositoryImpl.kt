package com.lagrik.weatherapp.data.network.repository

import com.lagrik.weatherapp.data.network.response.JsonWeatherData
import com.lagrik.weatherapp.data.network.service.RetrofitService
import com.lagrik.weatherapp.data.network.toWeatherModel
import com.lagrik.weatherapp.domain.model.WeatherModel
import com.lagrik.weatherapp.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
) : ForecastRepository {
    private var weatherData = JsonWeatherData()
    private val listForecast = ArrayList<WeatherModel>()
    private var model: WeatherModel? = null

    override suspend fun getWeatherData(city: String) {
        listForecast.clear()
        weatherData = retrofitService.getForecastAsync(city).await()
    }

    override suspend fun getForecastList(city: String): List<WeatherModel> {
        getWeatherData(city)
        weatherData.forecast?.forecastDay?.map {
            listForecast.add(it.toWeatherModel())
        }
        return listForecast
    }

    override suspend fun getCurrentDay(): WeatherModel {
        model = weatherData.current?.toWeatherModel(weatherData.location,
            listForecast[0])
        return model ?: WeatherModel("", "", "", "", "", "", "", "", "", "", emptyList())
    }
}