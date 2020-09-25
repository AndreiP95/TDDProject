package com.example.tdddemoproject.searchAlgorithmTests

import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.utils.searchAlgorithm
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchAlgorithmTests {
    private lateinit var cities: ArrayList<City>
    private var startTime: Long = 0
    private var finishTime: Long = 0

    /**
     * Setting up mocked city list before every test
     * Declaring the start time of each test for time-performance measurement
     */
    @Before
    fun setup() {
        cities = MockedCityList.getCities()
        startTime = System.currentTimeMillis()
    }

    /**
     * Setting up finish time for each test for time-performance measurement
     * Printing the time taken for a test to finish
     */
    @After
    fun afterTest() {
        finishTime = System.currentTimeMillis()
        print("Time taken: ")
        print(finishTime - startTime)
        println()
    }

    /**
     * The tests follow Right-BICEP and CORRECT structure and implements
     * them where is possible
     *
     * Right - checks if the tested functions returns the expected value
     */
    @Test
    fun searchAlgorithmRight() {
        val foundCities = searchAlgorithm(cities, "Bucharest")
        assertEquals("Bucharest", foundCities?.get(0)?.name)
    }

    /**
     * Cross Check - checks the result by using another method
     */
    @Test
    fun searchAlgorithmCrossCheck() {
        val citiesWithFirstLetter = countCities("Buc")
        assertTrue(
            citiesWithFirstLetter == searchAlgorithm(
                cities,
                "Buc"
            )?.size && cities.isNotEmpty()
        )
    }

    /**
     * Error Conditions - checks if the tested method handles error throwing
     */
    @Test(expected = IllegalArgumentException::class)
    fun searchAlgorithmErrorCondition() {
        searchAlgorithm(cities, "B")
    }

    /**
     * Performance - checks if the method runs in a specific time interval
     */
    @Test(timeout = 150)
    fun searchAlgorithmPerformance() {
        searchAlgorithm(cities, "Buc")
    }

    /**
     * Conformance - checks if the returned objects are the expected type
     */
    @Test
    fun searchAlgorithmConformance() {
        val foundCities = searchAlgorithm(cities, "Buc")
        assertTrue(foundCities?.get(0) is City)
    }

    /**
     * Ordering - check if the values of the given List are sorted
     */
    @Test
    fun searchAlgorithmOrdering() {
        var previous = ""
        var isInOrder = true
        val foundCities = searchAlgorithm(cities, "Buc")
        if (foundCities != null) {
            for (item in foundCities) {
                if (item.name != null && item.name.toString() < previous) {
                    isInOrder = false
                    break
                }
                previous = item.name.toString()
            }
            assertEquals(true, isInOrder)
        }
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