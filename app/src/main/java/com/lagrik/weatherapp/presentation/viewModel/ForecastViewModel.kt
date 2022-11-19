package com.lagrik.weatherapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lagrik.weatherapp.domain.model.HourModel
import com.lagrik.weatherapp.domain.model.WeatherModel
import com.lagrik.weatherapp.domain.usecase.GetCurrentDayUseCase
import com.lagrik.weatherapp.domain.usecase.GetForecastListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastListUseCase,
    private val getCurrentDayUseCase: GetCurrentDayUseCase,
) : ViewModel() {
    private val _currentDay = MutableLiveData<WeatherModel>()
    val currentDay: LiveData<WeatherModel> = _currentDay

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    val submitDay = MutableLiveData<WeatherModel>()

    private val _forecastList = MutableLiveData<List<WeatherModel>>()
    val forecastList: LiveData<List<WeatherModel>> = _forecastList

    fun getForecast(city: String) {
        viewModelScope.launch {
            try {
                _forecastList.value = getForecastUseCase.invoke(city)
                _currentDay.value = getCurrentDayUseCase.invoke()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun getHoursList(wItem: WeatherModel): List<HourModel> {
        val hours = wItem.hours
        val list = ArrayList<HourModel>()
        for (element in hours) {
            val item = HourModel(
                time = element.time,
                condition = element.condition,
                temp_c = element.temp_c
            )
            list.add(item)
        }
        return list
    }
}