package com.lagrik.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lagrik.weatherapp.R
import com.lagrik.weatherapp.databinding.ItemHourBinding
import com.lagrik.weatherapp.domain.model.HourModel
import com.squareup.picasso.Picasso

class HoursAdapter(
    private val hourList: List<HourModel>,
) : RecyclerView.Adapter<HoursAdapter.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemHourBinding.bind(view)

        fun bind(item: HourModel) = with(binding) {
            time.text = item.time
            avgTemp.text = item.temp_c
            Picasso.get().load(item.condition).into(conditionImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hour, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(hourList[position])
    }

    override fun getItemCount(): Int {
        return hourList.size
    }
}