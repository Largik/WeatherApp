package com.lagrik.weatherapp.domain.usecase

import com.lagrik.weatherapp.domain.model.WeatherModel
import com.lagrik.weatherapp.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastListUseCase @Inject constructor(
    private val repo: ForecastRepository,
) {
    suspend fun invoke(city: String): List<WeatherModel> = repo.getForecastList(city)
}