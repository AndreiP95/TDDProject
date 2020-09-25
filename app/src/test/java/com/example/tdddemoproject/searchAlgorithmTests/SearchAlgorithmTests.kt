package com.example.tdddemoproject.searchAlgorithmTests

import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.repo.model.CityCoordinate
import com.example.tdddemoproject.utils.searchAlgorithm
import org.junit.Before
import org.junit.Test


class SearchAlgorithmTests {
    private lateinit var cities: List<City>

    @Before
    fun setup() {
        //cities = arrayListOf()
        cities = MockedCityList.getCities()
    }

    @Test
    fun searchAlgorithmRight() {
        val foundCities = searchAlgorithm(cities, "Bucharest")
        assert(foundCities[0].name == "Bucharest")
    }

    @Test
    fun searchAlgorithmLowerBoundary() {
        assert(cities.size > 0 && cities.isNotEmpty())
    }

    @Test
    fun searchAlgorithmHigherBoundary() {
        assert(cities.size < 200000 && cities.isNotEmpty())
    }

    @Test
    fun searchAlgorithmCrossCheck() {
        val citiesWithFirstLetter = countCities("Buc")
        assert(citiesWithFirstLetter == searchAlgorithm(cities, "Buc").size)
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
        val city = City("test", "test", "test", CityCoordinate(10.00000, 10.00000))
        assert(searchAlgorithm(cities, "Citroen")[0]::class == city::class)
    }

    @Test
    fun searchAlgorithmOrdering() {
        var previous = ""
        var isInOrder = true
        for (item in searchAlgorithm(cities, "Buc")) {
            item.name?.let { name ->
                if (name < previous)
                    isInOrder = false
                previous = name
            }
        }
        assert(isInOrder)
    }


    private fun countCities(firstLetters: String): Int {
        var citiesWithFirstLetter = 0
        for (city in cities) {
            if (city.name?.take(3) == firstLetters) {
                citiesWithFirstLetter++
            }
        }
        return citiesWithFirstLetter
    }


}