package com.example.tdddemoproject.utils

import com.example.tdddemoproject.repo.model.City

fun searchAlgorithm(cities: List<City>, cityToFind: String): ArrayList<City> {
    val foundCities: ArrayList<City> = arrayListOf()
    for (item in cities) {
        if (item.name?.take(cityToFind.length) == cityToFind) {
            foundCities.add(item)
        }
    }
    return foundCities
}
