package com.example.tdddemoproject.searchAlgorithmTests

import com.example.tdddemoproject.BaseApplication
import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.utils.trie.Trie
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

class SearchAlgorithmTests {
    private var cities = MockedCityList.getCities()
    private val trie by inject(Trie::class.java)

    companion object {
        private var isInitialized = false
    }

    /**
     * Setting up mocked city list before every test
     * Setting up Trie before each test
     */
    @Before
    fun setup() {
        if (!isInitialized) {
            val app = BaseApplication()
            startKoin {
                androidContext(app)
                modules(app.trieModule)
            }
            trie.populateTrie(cities)
            isInitialized = true
        }
    }

    /**
     * The tests follow Right-BICEP and CORRECT structure and implements
     * them where is possible
     *
     * Right - checks if the tested functions returns the expected value
     */
    @Test
    fun searchAlgorithmRight() {
        val foundCities = trie.findCitiesWith("Bucharest")
        assertEquals("Bucharest", foundCities?.get(0)?.name)
    }

    /**
     * Right - checks if the functions returns all cities if there is no input
     */
    @Test
    fun searchAlgorithmRightNoInput() {
        val foundCities = trie.findCitiesWith("")
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
    fun searchAlgorithmRightNoCitiesFound() {
        val foundCities = trie.findCitiesWith("NonExistingCity")
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
            citiesWithFirstLetter == trie.findCitiesWith(
                "Buc"
            )?.size && cities.isNotEmpty()
        )
    }

    /**
     * Error conditions - checks if the algorithm handles wrong inputs
     *
     */
    @Test(expected = IllegalArgumentException::class)
    fun searchAlgorithmErrorConditions() {
        val citiesListWithEmptyCity = arrayListOf<City>()
        citiesListWithEmptyCity.add(City())
        trie.populateTrie(citiesListWithEmptyCity)
    }

    /**
     * Performance - checks if the method runs in a specific time interval
     */
    @Test(timeout = 150)
    fun searchAlgorithmPerformance() {
        trie.findCitiesWith("Buc")
    }

    /**
     * Conformance - checks if the returned objects are the expected type
     */
    @Test
    fun searchAlgorithmConformance() {
        val foundCities = trie.findCitiesWith("Buc")
        assertTrue(foundCities?.get(0) is City)
    }

    /**
     * A helper method that is needed to create a Cross Check test
     *
     * @param firstLetters - mocked user's input
     * @return - the number of cities that starts with the given input
     */
    private fun countCities(firstLetters: String): Int {
        return cities.count {
            it.name.toString().startsWith(firstLetters) ||
                    it.name.toString().startsWith(firstLetters.toLowerCase())
        }
    }

}
