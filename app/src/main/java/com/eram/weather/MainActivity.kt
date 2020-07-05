package com.eram.weather

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.eram.domain.entity.TimeState
import com.eram.domain.entity.Weather
import com.eram.weather.adapter.DaysAdapter
import com.eram.weather.adapter.TimesAdapter
import com.eram.weather.mapper.getWeatherIcon
import com.eram.weather.utils.SpanningLinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var toast: Toast? = null
    private lateinit var daysAdapter: DaysAdapter
    private lateinit var timesAdapter: TimesAdapter
    private lateinit var cityData: List<Pair<Int, String>>

    private val mainVM: MainVM by viewModels()
    private var currentTimeState: TimeState? = null
    private var isNotNowWeather = false

    companion object {
        @JvmStatic
        private var context2: Context? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        context2 = this

        registerObservers()
        mainVM.getCurrentTimeState()

        val cityIds = resources.getIntArray(R.array.city_ids)
        val cityNames = resources.getStringArray(R.array.city_name)
        cityData = cityIds.zip(cityNames)
        mainVM.setCurrentCityId(cityData.first().first)
    }

    @SuppressLint("SetTextI18n")
    private fun registerObservers() {
        mainVM.currentCityId.observe(this, Observer {
            mainVM.getCurrentWeather()
            mainVM.getMultipleDaysWeather()
            mainVM.getMultipleTimesWeather()
            isNotNowWeather = false
        })

        mainVM.error.observe(this, Observer {
            toast?.cancel()
            toast = Toast.makeText(this, it.message, Toast.LENGTH_LONG)
            toast?.show()
        })

        mainVM.currentTimeState.observe(this, Observer { timeState ->
            setBackgroundContainer(timeState)
            currentTimeState = timeState
        })

        mainVM.currentWeather.observe(this, Observer {
            txtDegree.text = "${it.temp}°"
            if (it.min != it.max)
                txtDegreeRange.text = "${it.min}°/${it.max}°"
            else
                txtDegreeRange.text = ""
            setWeatherIcon(it)
        })

        mainVM.multipleDaysWeather.observe(this, Observer {
            daysAdapter.setData(it, currentTimeState!!)
        })

        mainVM.multipleTimeWeather.observe(this, Observer {
            timesAdapter.setData(it, currentTimeState!!)
        })
    }

    private fun setBackgroundContainer(timeState: TimeState?) {
        container.setBackgroundResource(
            when (timeState) {
                TimeState.Dawn ->
                    R.drawable.background_dawn
                TimeState.Night ->
                    R.drawable.background_night
                TimeState.Morning ->
                    R.drawable.background_morning
                TimeState.Evening ->
                    R.drawable.background_evening
                TimeState.Noon ->
                    R.drawable.background_noon
                else ->
                    R.drawable.background_noon
            }
        )
    }

    private fun setWeatherIcon(weather: Weather) {
        imgIcon.setImageResource(getWeatherIcon(weather.state, currentTimeState!!))
    }


    private fun initViews() {
        rvTimes.layoutManager = SpanningLinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        timesAdapter = TimesAdapter(this)
        rvTimes.adapter = timesAdapter

        rvDays.layoutManager = LinearLayoutManager(this)
        daysAdapter = DaysAdapter(this) { weather ->
            isNotNowWeather = true
            mainVM.setCurrentWeather(weather)
        }
        rvDays.adapter = daysAdapter

        spCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainVM.setCurrentCityId(cityData[position].first)
            }

        }
    }

    override fun onPause() {
        super.onPause()
        toast?.cancel()
    }

    override fun onBackPressed() {
        if (isNotNowWeather) {
            isNotNowWeather = false
            mainVM.setCurrentCityId(cityData[spCity.selectedItemPosition].first)
        } else
            super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }
}