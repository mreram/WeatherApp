package com.eram.data.remote.model

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastResult>,
    val message: Int
)

data class ForecastResult(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: ForecastMain,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind
)


data class ForecastMain(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
)