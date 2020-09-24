package com.example.tdddemoproject.searchcities

import junit.framework.Assert.assertEquals
import org.junit.Test

class SearchCitiesViewModelTest {

    private val citiesList = arrayListOf(
        MockCityModel("Galati", "RO"),
        MockCityModel("Bucharest", "RO"),
        MockCityModel("Alabama", "US"),
        MockCityModel("Sydney", "AU"),
        MockCityModel("Cluj", "RO"),
        MockCityModel("Albuquerque", "US")
    )

    @Test
    fun checkFirstCities() {
        citiesList.sortBy {
            it.name
        }

        assertEquals("Alabama", citiesList[0].name)
        assertEquals("Albuquerque", citiesList[1].name)
    }

    @Test
    fun checkNonExistingCity() {
        var cityExists = false
        for (city: MockCityModel in citiesList) {
            if (city.name == "Arad") {
                cityExists = true
                break
            }
        }

        assertEquals(false, cityExists)
    }

    @Test
    fun getCityByName() {
        val citiesReturned = arrayListOf<MockCityModel>()

        val cityName = MockCityModel("Galati", "RO")

        citiesList.sortBy {
            it.name
        }

        for (city: MockCityModel in citiesList) {
            if (city.name == cityName.name) {
                citiesReturned.add(cityName)
            }
        }

        assertEquals("Galati", citiesReturned[0].name)
        assertEquals(1, citiesReturned.size)
    }

    @Test
    fun getCityByPrefix() {
        val prefix = "Alb"
        val citiesWithPrefix = arrayListOf<MockCityModel>()

        for (city: MockCityModel in citiesList) {
            if (city.name.contains(prefix)) {
                citiesWithPrefix.add(city)
            }
        }

        citiesWithPrefix.sortBy {
            it.name
        }

        assertEquals("Albuquerque", citiesWithPrefix[0].name)
        assertEquals(1, citiesWithPrefix.size)
    }


}