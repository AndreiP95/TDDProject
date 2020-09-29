package com.example.tdddemoproject.searchAlgorithmTests

import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.utils.Trie
import com.example.tdddemoproject.utils.Trie.Companion.initTrie
import com.example.tdddemoproject.utils.searchAlgorithm
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchAlgorithmTests {
    private lateinit var cities: ArrayList<City>
    private lateinit var trie: Trie

    /**
     * Setting up mocked city list before every test
     * Setting up Trie before each test
     */
    @Before
    fun setup() {
        cities = MockedCityList.getCities()
        trie = initTrie(cities)
    }

    /**
     * The tests follow Right-BICEP and CORRECT structure and implements
     * them where is possible
     *
     * Right - checks if the tested functions returns the expected value
     */
    @Test
    fun searchAlgorithmRight() {
        val foundCities = searchAlgorithm(trie, "Bucharest")
        assertEquals("Bucharest", foundCities?.get(0)?.name)
    }

    /**
     * Right - checks if the functions returns all cities if there is no input
     */
    @Test
    fun searchAlgorithmRightNoInput() {
        val foundCities = searchAlgorithm(trie, "")
        if (foundCities != null) {
            assertTrue(cities.size == foundCities.size)
        }
    }

    /**
     * Right - if there is no existing input in the Trie,
     * an empty ArrayList will be returned
     *
     */
    @Test
    fun searchAlgorithmRightNoCitiesFound(){
        val foundCities = searchAlgorithm(trie, "NonExistingCity")
        if (foundCities != null) {
            assertTrue(foundCities.isEmpty())
        }
    }

    /**
     * Cross Check - checks the result by using another method
     */
    @Test
    fun searchAlgorithmCrossCheck() {
        val citiesWithFirstLetter = countCities("Buc")
        assertTrue(
            citiesWithFirstLetter == searchAlgorithm(
                trie,
                "Buc"
            )?.size && cities.isNotEmpty()
        )
    }

    /**
     * Performance - checks if the method runs in a specific time interval
     */
    @Test(timeout = 150)
    fun searchAlgorithmPerformance() {
        searchAlgorithm(trie, "Buc")
    }

    /**
     * Conformance - checks if the returned objects are the expected type
     */
    @Test
    fun searchAlgorithmConformance() {
        val foundCities = searchAlgorithm(trie, "Buc")
        assertTrue(foundCities?.get(0) is City)
    }

    /**
     * A helper method that is needed to create a Cross Check test
     *
     * @param firstLetters - mocked user's input
     * @return - the number of cities that starts with the given input
     */
    private fun countCities(firstLetters: String): Int {
        return cities.count { it.name.toString().startsWith(firstLetters) }
    }

}
