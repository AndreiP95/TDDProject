package com.example.tdddemoproject.searchcities

import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.utils.trie.Trie
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test

class SearchCitiesViewModelTest {

    private val citiesList = listOf(
        City("Galati", "RO"),
        City("Bucharest", "RO"),
        City("Alabama", "US"),
        City("Calarasi", "Ro"),
        City("Sydney", "AU"),
        City("Cluj", "RO"),
        City("Constanta", "RO"),
        City("Albuquerque", "US")
    )

    private var trie: Trie = Trie()

    @Before
    fun setup() {
        trie.populateTrie(citiesList)
    }

    /**
     * Checks if the first city is Alabama
     */
    @Test
    fun checkFirstCities() {
        assertTrue(trie.findCitiesWith("")?.get(0)?.name == "Alabama")
    }

    /**
     * Check if there is no city with the name of "Arad"
     */
    @Test
    fun checkNonExistingCity() {
        val citiesWithPrefix = trie.findCitiesWith("")
        if (citiesWithPrefix != null) {
            assertFalse(citiesWithPrefix.any {
                it.name == "Arad"
            })
        }
    }

    /**
     * Checks if a city with the given name is in the list
     */
    @Test
    fun getCityByName() {
        val cityName = MockCityModel("Galati", "RO")

        val citiesWithPrefix = trie.findCitiesWith("Galati")
        if (citiesWithPrefix != null) {
            assertTrue(citiesWithPrefix.find {
                it.name == cityName.name
            } != null)
        }
    }

    /**
     * Checks if there is a city which starts with a given prefix
     */
    @Test
    fun getCityByPrefix() {
        val prefix = "Alb"
        val citiesReturned = trie.findCitiesWith("Alb")
        if (citiesReturned != null) {
            assertTrue(citiesReturned.any {
                it.name!!.startsWith(prefix)
            })
        }
    }

    /**
     * Checks if there are more cities with a given prefix
     */
    @Test
    fun checkMoreCitiesReturned() {
        val prefix = "C"
        val citiesReturned = trie.findCitiesWith(prefix)

        if (citiesReturned != null) {
            assertTrue(citiesReturned.size == 3)
        }
    }

}