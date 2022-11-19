package com.lagrik.weatherapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.lagrik.weatherapp.databinding.FragmentForecastBinding
import com.lagrik.weatherapp.domain.model.WeatherModel
import com.lagrik.weatherapp.presentation.adapters.ForecastAdapter
import com.lagrik.weatherapp.presentation.adapters.getQueryTextChangeStateFlow
import com.lagrik.weatherapp.presentation.viewModel.ForecastViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForecastFragment : Fragment(), ForecastAdapter.Listener {
    private lateinit var binding: FragmentForecastBinding
    private lateinit var adapter: ForecastAdapter

    private val vm: ForecastViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorObserver()
        updateCard()
        searchWeatherInCity()
    }

    @OptIn(FlowPreview::class)
    private fun searchWeatherInCity() = with(binding) {
        lifecycleScope.launch {
            selectCity.getQueryTextChangeStateFlow()
                .debounce(SEARCH_TIMEOUT)
                .distinctUntilChanged()
                .collectLatest { q ->
                    if (q.isEmpty() || q == "") {
                        vm.getForecast("Ulyanovsk")
                    } else {
                        vm.getForecast(q)
                    }
                }
        }
    }

    private fun updateCard() = with(binding) {
        vm.forecastList.observe(viewLifecycleOwner) {
            adapter = ForecastAdapter(it, this@ForecastFragment)
            forecastRecyclerList.adapter = adapter
        }
        vm.currentDay.observe(viewLifecycleOwner) {
            todayText.text = it.city
            submitLocation.text = it.submitLocation
            lastUpdate.text = it.date
            avgTemp.text = it.avgTemp
            minTemp.text = it.minTemp
            maxTemp.text = it.maxTemp
            condition.text = it.condition
            humidity.text = it.humidity
            windKph.text = it.wind_kph
            Picasso.get().load(it.imageUrl).into(conditionImg)
        }
    }

    private fun errorObserver() {
        vm.error.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), SEARCH_ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(item: WeatherModel) {
        vm.submitDay.value = item
    }

    companion object {
        const val SEARCH_TIMEOUT = 500L
        const val SEARCH_ERROR_MESSAGE = "No matching location found."
    }
}