package com.example.tdddemoproject.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.utils.trie.Trie
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchCitiesViewModel : ViewModel(), KoinComponent {

    var citiesLiveData: MutableLiveData<ArrayList<City>> = MutableLiveData()
    private val trie = inject<Trie>()

    fun populateList() {
        citiesLiveData.value = trie.value.findCitiesWith("")
    }

    fun getCitiesWithPrefix(s: String) {
        citiesLiveData.value = trie.value.findCitiesWith(s)
    }


}