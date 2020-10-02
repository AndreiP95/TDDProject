package com.example.tdddemoproject.searchAlgorithmTests

import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.repo.model.CityCoordinate
import com.example.tdddemoproject.utils.GenerateCities
import kotlin.random.Random

object MockedCityList {
    /**
     * Generates an ArrayList of random Cities for test purposes
     *
     * @return - An ArrayList of Cities
     */
    fun getCities(): ArrayList<City> {
        val cities: ArrayList<City> = arrayListOf()
        GenerateCities.initCities(cities)
        return cities
    }
}