package com.eram.weather.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eram.domain.entity.TimeState
import com.eram.domain.entity.Weather
import com.eram.weather.R
import com.eram.weather.mapper.getWeatherIcon
import kotlinx.android.synthetic.main.item_days.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DaysAdapter(private val context: Context, private val itemClick: (weather: Weather) -> Unit) :
    RecyclerView.Adapter<DaysAdapter.Holder>() {

    private var _data: MutableList<Weather>? = null
    private val icons = arrayListOf<Int>()

    private val days = arrayListOf<String>()

    init {
        val sdf = SimpleDateFormat("EEEE", Locale.US)
        days.add(context.getString(R.string.tomorrow))
        for (i in 2..7) {
            val calendar: Calendar = GregorianCalendar()
            calendar.add(Calendar.DATE, i)
            days.add(sdf.format(calendar.time))
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDay: TextView = itemView.txtDay
        val imgIcon: ImageView = itemView.imgIcon
        val txtDegreeRange: TextView = itemView.txtDegreeRange

        init {
            itemView.setOnClickListener {
                _data?.get(adapterPosition)?.let { itemClick(it) }
            }
        }
    }

    fun setData(
        data: List<Weather>,
        currentTimeState: TimeState
    ) {
        if (_data == null)
            _data = ArrayList()
        _data = ArrayList(data)

        icons.clear()
        _data?.forEach {
            icons.add(getWeatherIcon(it.state, currentTimeState))
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.item_days, parent, false)
        return Holder(inflate)
    }

    override fun getItemCount(): Int {
        return _data?.size ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = _data?.get(position)
        holder.txtDay.text = days[position]
        holder.txtDegreeRange.text = "${item?.min}°/${item?.max}°"
        holder.imgIcon.setImageResource(icons[position])
    }

}