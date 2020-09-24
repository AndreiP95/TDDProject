package com.example.tdddemoproject.searchAlgorithmTests
import com.example.tdddemoproject.model.City
import com.example.tdddemoproject.model.Coodrinates
import com.example.tdddemoproject.utils.searchAlgorithm
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class SearchAlgorithmTests {
    private lateinit var cities: ArrayList<City>
    @Before
    fun setup() {
        //cities = arrayListOf()
        cities = MockedCityList.getCities()
    }

    @Test
    fun searchAlgorithmRight() {
        val foundCities = searchAlgorithm(cities, "Bucharest")
        assertEquals(foundCities[0].name, "Bucharest")
    }

    @Test
    fun searchAlgorithmLowerBoundary() {
        assertTrue(cities.size > 0 && cities.isNotEmpty())
    }

    @Test
    fun searchAlgorithmHigherBoundary() {
        assertTrue(cities.size < 200000 && cities.isNotEmpty())
    }

    @Test
    fun searchAlgorithmCrossCheck() {
        val citiesWithFirstLetter = countCities("Buc")
        assertTrue(citiesWithFirstLetter == searchAlgorithm(cities, "Buc").size)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun searchAlgorithmErrorCondition() {
        searchAlgorithm(cities, "B")
    }

    @Test(timeout = 1000)
    fun searchAlgorithmPerformance() {
        searchAlgorithm(cities, "B")
    }

    @Test
    fun searchAlgorithmConformance() {
        val city = City("test", "test", "test", Coodrinates(10.00000, 10.00000))
        assertTrue(searchAlgorithm(cities, "Citroen")[0]::class == city::class)
    }

    @Test
    fun searchAlgorithmOrdering() {
        var previous = ""
        var isInOrder = true
        for (item in searchAlgorithm(cities, "Buc")) {
            if (item.name < previous)
                isInOrder = false
            previous = item.name
        }
        assertEquals(isInOrder, true)
    }


    private fun countCities(firstLetters: String): Int {
        var citiesWithFirstLetter = 0
        for (city in cities) {
            if (city.name.take(3) == firstLetters) {
                citiesWithFirstLetter++
            }
        }
        return citiesWithFirstLetter
    }

}