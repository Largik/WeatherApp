package com.lagrik.weatherapp.di

import com.lagrik.weatherapp.data.network.repository.ForecastRepositoryImpl
import com.lagrik.weatherapp.domain.repository.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindForecastRepository(
        forecastRepo: ForecastRepositoryImpl,
    ): ForecastRepository
}