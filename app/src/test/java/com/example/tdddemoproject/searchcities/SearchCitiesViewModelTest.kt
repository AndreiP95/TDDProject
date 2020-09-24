package com.example.tdddemoproject.searchcities

import junit.framework.Assert.*
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
        assertFalse(citiesList.any { it.name.equals("Arad") })
    }

    @Test
    fun getCityByName() {
        val cityName = MockCityModel("Galati", "RO")

        citiesList.sortBy {
            it.name
        }

        assertTrue(citiesList.find {
            it.name == cityName.name
        } != null)
    }

    @Test
    fun getCityByPrefix() {
        val prefix = "Alb"

        assertTrue(citiesList.find {
            it.name.contains(prefix)
        } != null)

        citiesList.removeIf {
            !it.name.contains(prefix)
        }

        assertTrue(citiesList.size == 1)
    }
}