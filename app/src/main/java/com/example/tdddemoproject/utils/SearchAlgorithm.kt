package com.example.tdddemoproject.utils

import com.example.tdddemoproject.repo.model.City


fun searchAlgorithm(cityToFind: String): ArrayList<City>? {
    if(cityToFind.length >= 3){
        val citiesTrie = GenerateCities.getCitiesTrie()
        val foundCities =  citiesTrie.findCitiesThatStartWith(cityToFind)
        println()
        print(foundCities.size)
        return foundCities
    } else throw IllegalArgumentException("Search should start at 3 chars!")
}
