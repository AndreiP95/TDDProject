package com.example.tdddemoproject.searchAlgorithmTests
import com.example.tdddemoproject.model.City
import com.example.tdddemoproject.model.Coodrinates
import com.example.tdddemoproject.utils.searchAlgorithm
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException


class SearchAlgorithmTests {
    private lateinit var cities: ArrayList<City>
    private var startTime: Long = 0
    private var finishTime: Long = 0
    @Before
    fun setup() {
        cities = MockedCityList.getCities()
        startTime = System.currentTimeMillis()
    }

    @After
    fun afterTest(){
        finishTime = System.currentTimeMillis()
        print("Time taken: ")
        print(finishTime - startTime)
        println()
    }
    @Test
    fun searchAlgorithmRight() {
        val foundCities = searchAlgorithm( "Bucharest")
        assertEquals(foundCities?.get(0)?.name, "Bucharest")
    }

    @Test
    fun searchAlgorithmLowerBoundary() {
        assertTrue(cities.size > 0 && cities.isNotEmpty())
    }

    @Test
    fun searchAlgorithmCrossCheck() {
        val citiesWithFirstLetter = countCities("Buc")
        assertTrue(citiesWithFirstLetter == searchAlgorithm("Buc")?.size)
    }

    @Test(expected = IllegalArgumentException::class)
    fun searchAlgorithmErrorCondition() {
        searchAlgorithm("B")
    }

    @Test(timeout = 150)
    fun searchAlgorithmPerformance() {
        searchAlgorithm( "Buc")
    }

//    @Test
//    fun searchAlgorithmConformance() {
//        val city = City("test", "test", "test", Coodrinates(10.00000, 10.00000))
//        assertTrue(searchAlgorithm( "Citroen")?.get(0)::class == city::class)
//    }

    @Test
    fun searchAlgorithmOrdering() {
        var previous = ""
        var isInOrder = true
        for (item in searchAlgorithm( "Buc")!!) {
            if (item.name < previous) {
                isInOrder = false
                break
            }
            previous = item.name
        }
        assertEquals(isInOrder, true)
    }


    private fun countCities(firstLetters: String): Int {
        return cities.count { it.name.startsWith(firstLetters) }
    }

}