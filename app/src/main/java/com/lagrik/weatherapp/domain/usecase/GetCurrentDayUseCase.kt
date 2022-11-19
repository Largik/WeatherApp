package com.lagrik.weatherapp.domain.usecase

import com.lagrik.weatherapp.domain.model.WeatherModel
import com.lagrik.weatherapp.domain.repository.ForecastRepository
import javax.inject.Inject

class GetCurrentDayUseCase @Inject constructor(
    private val repo: ForecastRepository,
) {
    suspend fun invoke(): WeatherModel = repo.getCurrentDay()
}