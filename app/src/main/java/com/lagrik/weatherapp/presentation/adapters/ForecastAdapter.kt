@file:JvmName("ForecastAdapterKt")

package com.lagrik.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lagrik.weatherapp.R
import com.lagrik.weatherapp.databinding.ItemForecastBinding
import com.lagrik.weatherapp.domain.model.WeatherModel
import com.lagrik.weatherapp.presentation.fragment.ForecastFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ForecastAdapter(
    private val forecastList: List<WeatherModel>,
    private val listener: Listener,
) : RecyclerView.Adapter<ForecastAdapter.Holder>() {
    class Holder(view: View, private val listener: Listener) : RecyclerView.ViewHolder(view) {
        private val binding = ItemForecastBinding.bind(view)
        private var dayClick: WeatherModel? = null

        init {
            itemView.setOnClickListener {
                dayClick?.let { day ->
                    listener.onClick(day)
                    goToDetail(itemView)
                }
            }
        }

        fun bind(item: WeatherModel) = with(binding) {
            dayClick = item
            date.text = item.date
            avgTemp.text = item.avgTemp
            minTemp.text = item.minTemp
            maxTemp.text = item.maxTemp
            Picasso.get().load(item.imageUrl).into(conditionImg)
        }

        private fun goToDetail(view: View) {
            val action =
                ForecastFragmentDirections.actionForecastFragmentToDetailFragment()
            view.findNavController().navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return Holder(view, listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    interface Listener {
        fun onClick(item: WeatherModel)
    }
}

fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {
    val searchQuery = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            this@getQueryTextChangeStateFlow.clearFocus()
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            searchQuery.value = newText ?: ""
            return true
        }
    })
    return searchQuery
}