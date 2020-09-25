package com.example.tdddemoproject.searchcities

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

        assert("Alabama" == citiesList[0].name)
        assert("Albuquerque" == citiesList[1].name)
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

        assert(!cityExists)
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

        assert(citiesReturned[0].name == "Galati")
        assert(citiesReturned.size == 1)
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

        assert(citiesList.sortBy {
            it.name
        }.let {
            citiesList[0].name == "Alabama"
            citiesList[1].name == "Albuquerque"
        })


        assert(citiesWithPrefix[0].name == "Albuquerque")
        assert(citiesWithPrefix.size == 1)
    }


}