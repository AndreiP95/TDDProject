package com.example.tdddemoproject.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tdddemoproject.R
import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.ui.search.adapter.CityAdapter
import kotlinx.android.synthetic.main.fragment_search_cities.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

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
    }

    private fun addCities() {
        searchCitiesViewModel.populateList()

        searchCitiesViewModel.citiesLiveData.observe(
            viewLifecycleOwner,
            object : Observer<ArrayList<City>> {
                override fun onChanged(t: ArrayList<City>?) {
                    recycler_view_cities.layoutManager = LinearLayoutManager(context)
                    recycler_view_cities.adapter = t?.let { CityAdapter(it) }
                    recycler_view_cities.adapter?.notifyDataSetChanged()
                }
            })
    }


}