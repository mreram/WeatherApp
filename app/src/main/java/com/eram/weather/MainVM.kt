package com.eram.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eram.domain.GetCurrentTimeState
import com.eram.domain.GetCurrentWeather
import com.eram.domain.GetMultipleDaysWeather
import com.eram.domain.GetMultipleTimesWeather
import com.eram.domain.base.UseCaseCallback
import com.eram.domain.entity.RequestMultipleWeather
import com.eram.domain.entity.TimeState
import com.eram.domain.entity.Weather
import com.eram.domain.exceptions.ErrorModel
import kotlinx.coroutines.launch

class MainVM @ViewModelInject constructor(
    private val getCurrentWeather: GetCurrentWeather,
    private val getCurrentTimeState: GetCurrentTimeState,
    private val getMultipleDaysWeather: GetMultipleDaysWeather,
    private val getMultipleTimesWeather: GetMultipleTimesWeather
) :
    ViewModel() {

    private val _currentWeather by lazy { MutableLiveData<Weather>() }
    val currentWeather: LiveData<Weather> get() = _currentWeather

    private val _error by lazy { MutableLiveData<ErrorModel>() }
    val error: LiveData<ErrorModel> get() = _error

    private val _currentTimeState by lazy { MutableLiveData<TimeState>() }
    val currentTimeState: LiveData<TimeState> get() = _currentTimeState

    private val _multipleDaysWeather by lazy { MutableLiveData<List<Weather>>() }
    val multipleDaysWeather: LiveData<List<Weather>> get() = _multipleDaysWeather

    private val _multipleTimeWeather by lazy { MutableLiveData<List<Weather>>() }
    val multipleTimeWeather: LiveData<List<Weather>> get() = _multipleTimeWeather

    private val _currentCityId by lazy { MutableLiveData<Int>() }
    val currentCityId: LiveData<Int> get() = _currentCityId

    fun getCurrentWeather() {
        viewModelScope.launch {
            getCurrentWeather.call(currentCityId.value, object : UseCaseCallback<Weather> {
                override fun onSuccess(result: Weather) {
                    _currentWeather.value = result
                }

                override fun onError(errorModel: ErrorModel?) {
                    _error.value = errorModel
                }
            })

        }
    }

    fun getCurrentTimeState() {
        viewModelScope.launch {
            getCurrentTimeState.call(onResult = object : UseCaseCallback<TimeState> {
                override fun onSuccess(result: TimeState) {
                    _currentTimeState.value = result
                }

                override fun onError(errorModel: ErrorModel?) {
                    _error.value = errorModel
                }
            })
        }
    }

    fun getMultipleDaysWeather() {
        viewModelScope.launch {
            getMultipleDaysWeather.call(
                RequestMultipleWeather(currentCityId.value, 7),
                object : UseCaseCallback<List<Weather>> {
                    override fun onSuccess(result: List<Weather>) {
                        _multipleDaysWeather.value = result
                    }

                    override fun onError(errorModel: ErrorModel?) {
                        _error.value = errorModel
                    }
                })

        }
    }

    fun getMultipleTimesWeather() {
        viewModelScope.launch {
            getMultipleTimesWeather.call(
                RequestMultipleWeather(currentCityId.value, 7),
                object : UseCaseCallback<List<Weather>> {
                    override fun onSuccess(result: List<Weather>) {
                        _multipleTimeWeather.value = result
                    }

                    override fun onError(errorModel: ErrorModel?) {
                        _error.value = errorModel
                    }
                })

        }
    }

    fun setCurrentCityId(value: Int) {
        _currentCityId.value = value
    }

    fun setCurrentWeather(value: Weather) {
        _currentWeather.value = value
    }
}