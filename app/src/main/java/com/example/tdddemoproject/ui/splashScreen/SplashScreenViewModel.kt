package com.example.tdddemoproject.ui.splashScreen

import androidx.lifecycle.ViewModel
import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.utils.trie.Trie
import org.koin.core.KoinComponent
import org.koin.core.inject

class SplashScreenViewModel : ViewModel(), KoinComponent {

    private val trie by inject<Trie>()

    fun initTrieList(cities: ArrayList<City>) {
        val sortedCityList: List<City> = cities.sortedWith(
            compareBy(
                { it.name },
                { it.country })
        )
        sortedCityList.let { trie.populateTrie(it) }
    }


}