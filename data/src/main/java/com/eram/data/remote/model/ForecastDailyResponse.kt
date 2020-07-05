package com.eram.data.remote.model

data class ForecastDailyResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastDailyResult>,
    val message: Double
)

data class ForecastDailyResult(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    val feels_like: FeelsLike,
    val humidity: Int,
    val pressure: Int,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weather: List<Weather>
)

data class Temp(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)

data class FeelsLike(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
)

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)