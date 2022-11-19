package com.lagrik.weatherapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lagrik.weatherapp.databinding.FragmentDetailBinding
import com.lagrik.weatherapp.presentation.adapters.HoursAdapter
import com.lagrik.weatherapp.presentation.viewModel.ForecastViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var adapter: HoursAdapter

    private val vm: ForecastViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancelDetail()
        initRcView()
        getItem()
    }

    private fun getItem() = with(binding) {
        vm.submitDay.observe(viewLifecycleOwner) {
            date.text = it.date
            avgTemp.text = it.avgTemp
            minTemp.text = it.minTemp
            maxTemp.text = it.maxTemp
            condition.text = it.condition
            humidity.text = it.humidity
            windKph.text = it.wind_kph
            Picasso.get().load(it.imageUrl).into(conditionImg)
        }
    }

    private fun cancelDetail() = with(binding) {
        cancelButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initRcView() = with(binding) {
        vm.submitDay.observe(viewLifecycleOwner) {
            adapter = HoursAdapter(vm.getHoursList(it))
            hoursList.adapter = adapter
        }
    }
}