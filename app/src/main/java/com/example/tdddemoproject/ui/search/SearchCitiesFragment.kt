package com.example.tdddemoproject.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tdddemoproject.R
import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.ui.search.adapter.CityAdapter
import com.example.tdddemoproject.utils.afterTextChanged
import kotlinx.android.synthetic.main.fragment_search_cities.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCitiesFragment : Fragment() {

    private val searchCitiesViewModel by viewModel<SearchCitiesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        addCities()
        changeListByPrefix()
    }

    private fun addCities() {
        searchCitiesViewModel.getCitiesWithPrefix()

        searchCitiesViewModel.citiesLiveData.observe(
            viewLifecycleOwner,
            object : Observer<ArrayList<City>> {
                override fun onChanged(citiesWithPrefix: ArrayList<City>?) {
                    recycler_view_cities.layoutManager = LinearLayoutManager(context)
                    recycler_view_cities.adapter = citiesWithPrefix?.let { CityAdapter(it) }
                    recycler_view_cities.adapter?.notifyDataSetChanged()
                }
            })
    }


    private fun changeListByPrefix() {
        et_search_city.afterTextChanged { city ->
            showProgressBar()
            hideNoCityForPrefixError()
            changeColorOfLayout(R.color.background_gray)

            lifecycleScope.launch {
                delay(200)
                searchCitiesViewModel.getCitiesWithPrefix(city)
                searchCitiesViewModel.citiesLiveData.value?.let { cityList ->
                    validateCityList(cityList, city)
                }
            }
        }
    }

    fun validateCityList(cityList: ArrayList<City>, city: String) {
        if (cityList.isNullOrEmpty()) {
            showCityError(city)
            hideRecyclerViewCities()
        } else {
            hideCityError()
            showRecyclerViewCities()
        }
    }

    fun hideCityError() {
        hideNoCityForPrefixError()
        changeColorOfLayout(R.color.background_white)
        hideProgressBar()
    }

    fun showCityError(city: String) {
        showCityNotFoundError(city)
        changeColorOfLayout(R.color.background_white)
        hideProgressBar()
    }

    fun changeColorOfLayout(color: Int) {
        layout_search_cities.setBackgroundColor(ContextCompat.getColor(requireContext(), color))
    }

    fun hideNoCityForPrefixError() {
        tv_no_cities.visibility = View.GONE
    }

    fun showCityNotFoundError(cityNotFound: String) {
        tv_no_cities.text = "Nu exista orase pentru cuvantul cheie $cityNotFound"
        tv_no_cities.visibility = View.VISIBLE
    }

    fun hideRecyclerViewCities() {
        recycler_view_cities.visibility = View.GONE
    }

    /*fun hideCityNotFoundError() {
        tv_no_cities.visibility = View.GONE
    }*/

    fun showRecyclerViewCities() {
        recycler_view_cities.visibility = View.VISIBLE
    }

    fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

}