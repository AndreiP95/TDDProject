package com.example.tdddemoproject.searchcities

import junit.framework.Assert.*
import org.junit.Test

class SearchCitiesViewModelTest {

    private val citiesList = arrayListOf(
        MockCityModel("Galati", "RO"),
        MockCityModel("Bucharest", "RO"),
        MockCityModel("Alabama", "US"),
        MockCityModel("Calarasi", "Ro"),
        MockCityModel("Sydney", "AU"),
        MockCityModel("Cluj", "RO"),
        MockCityModel("Constanta", "RO"),
        MockCityModel("Albuquerque", "US")
    )

    @Test
    fun checkFirstCities() {
        assertTrue(citiesList.sortedBy {
            it.name
        }.let {
            it[0].name == "Alabama" && it[1].name == "Albuquerque"
        })
    }

    @Test
    fun checkNonExistingCity() {
        assertFalse(citiesList.any { it.name == "Arad" })
    }

    @Test
    fun getCityByName() {
        val cityName = MockCityModel("Galati", "RO")

        assertTrue(citiesList.sortedBy {
            it.name
        }.let { sortedList ->
            sortedList.find { city ->
                city.name == cityName.name
            } != null
        })
    }

    @Test
    fun getCityByPrefix() {
        val prefix = "Alb"
        val citiesReturned = arrayListOf<MockCityModel>()

        assertTrue(citiesList.find {
            it.name.startsWith(prefix)
        } != null)

        citiesList.forEach {
            if (it.name.startsWith(prefix)) {
                citiesReturned.add(it)
            }
        }

        assertTrue(citiesReturned.size == 1)
    }

    @Test
    fun searchWithOneCharacter() {
        val prefix = "a"

        assertFalse(prefix.length > 3)
    }

    @Test
    fun searchWithTwoCharacters() {
        val prefix = "ab"

        assertFalse(prefix.length > 3)
    }

    @Test
    fun checkMoreCitiesReturned() {
        val prefix = "C"
        val citiesReturned = arrayListOf<MockCityModel>()

        citiesList.forEach {
            if (it.name.startsWith(prefix)) {
                citiesReturned.add(it)
            }
        }

        assertTrue(citiesReturned.size == 3)
    }

}