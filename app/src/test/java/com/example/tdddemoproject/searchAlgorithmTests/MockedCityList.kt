package com.example.tdddemoproject.searchAlgorithmTests

import com.example.tdddemoproject.model.City
import com.example.tdddemoproject.utils.GenerateCities

object MockedCityList {

    fun getCities(): ArrayList<City> {
        val cities: ArrayList<City> = arrayListOf()
        GenerateCities.initCities(cities)
        return cities
    }
}