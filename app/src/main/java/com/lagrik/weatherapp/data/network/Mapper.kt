package com.lagrik.weatherapp.data.network

import com.lagrik.weatherapp.data.network.response.Current
import com.lagrik.weatherapp.data.network.response.ForecastDay
import com.lagrik.weatherapp.data.network.response.Hour
import com.lagrik.weatherapp.data.network.response.Location
import com.lagrik.weatherapp.domain.model.HourModel
import com.lagrik.weatherapp.domain.model.WeatherModel
import java.text.SimpleDateFormat
import java.util.*

private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
private val formatterForTime = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH)
private val dayFormat = SimpleDateFormat("EEEE\n(dd.MM)", Locale.ENGLISH)
private val timeFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)

fun Current.toWeatherModel(location: Location?, currentDay: WeatherModel): WeatherModel =
    WeatherModel(
        city = Constant.TODAY + location?.city,
        submitLocation = location?.city + ", " + location?.country,
        date = Constant.LAST_UPDATE + timeUpdate,
        condition = condition?.text,
        avgTemp = tempC + Constant.CELSIUS,
        maxTemp = currentDay.maxTemp,
        minTemp = currentDay.minTemp,
        humidity = Constant.HUMIDITY + humidity,
        wind_kph = wind_kph + Constant.WIND,
        imageUrl = Constant.HTTP + condition?.icon,
        hours = currentDay.hours
    )

fun ForecastDay.toWeatherModel(): WeatherModel = WeatherModel(
    date = formatter.parse(date.toString())
        ?.let { dayFormat.format(it) },
    condition = day?.condition?.text,
    avgTemp = day?.avgTemp + Constant.CELSIUS,
    maxTemp = day?.maxTemp + Constant.CELSIUS,
    minTemp = day?.minTemp + Constant.CELSIUS,
    humidity = Constant.HUMIDITY + day?.humidity,
    wind_kph = day?.wind_kph + Constant.WIND,
    imageUrl = Constant.HTTP + day?.condition?.icon,
    hours = hours?.map { it.toHourModel() } ?: emptyList()
)

fun Hour.toHourModel(): HourModel = HourModel(
    time = formatterForTime.parse(time.toString())?.let { timeFormat.format(it) },
    condition = Constant.HTTP + condition?.icon,
    temp_c = temp_c + Constant.CELSIUS
)
