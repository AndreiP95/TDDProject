package com.example.tdddemoproject.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tdddemoproject.repo.model.City
import org.koin.core.KoinComponent

class SearchCitiesViewModel() : ViewModel(), KoinComponent {

    var citiesLiveData: MutableLiveData<ArrayList<City>> = MutableLiveData()
    private var cityArrayList = arrayListOf<City>()

    fun populateList() {
        cityArrayList.add(City("Cluj", "Ro"))
        cityArrayList.add(City("Galati", "Ro"))
        cityArrayList.add(City("Bucharest", "RO"))
        cityArrayList.add(City("Alabama", "US"))
        cityArrayList.add(City("Calarasi", "Ro"))
        cityArrayList.add(City("Sydney", "AU"))
        cityArrayList.add(City("Cluj", "RO"))
        cityArrayList.add(City("Constanta", "RO"))
        cityArrayList.add(City("Albuquerque", "US"))

        citiesLiveData.value = cityArrayList
    }

    fun getCitiesWithPrefix(s: CharSequence) {
        val citiesWithPrefix = arrayListOf<City>()
        for (city: City in cityArrayList) {
            if (city.name!!.startsWith(s)) {
                citiesWithPrefix.add(city)
            }
        }
        citiesLiveData.value = citiesWithPrefix
    }

}