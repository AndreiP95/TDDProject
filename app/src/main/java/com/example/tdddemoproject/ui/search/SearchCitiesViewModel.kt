package com.example.tdddemoproject.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.utils.searchAlgorithm
import com.example.tdddemoproject.utils.trie.Trie
import org.koin.core.KoinComponent
import java.util.*
import kotlin.collections.ArrayList

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

    fun getCitiesWithPrefix(s: String) {
        val trie = Trie.initTrie(cityArrayList)
        val citiesWithPrefix = searchAlgorithm(trie, s)
        citiesLiveData.value = citiesWithPrefix
    }

}