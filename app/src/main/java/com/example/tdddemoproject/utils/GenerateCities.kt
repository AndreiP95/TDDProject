package com.example.tdddemoproject.utils

import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.repo.model.CityCoordinate
import kotlin.random.Random

object GenerateCities {
    /**
     * Initializes the mocked city list
     *
     * @param cities - The List of cities in which mocked cities will be added
     */
    fun initCities(cities: ArrayList<City>) {
        generateCities(cities)
    }

    /**
     * Generates a number of random cities
     *
     * @param cities
     */
    private fun generateCities(cities: ArrayList<City>) {
        val cityNames: List<String> = listOf(
            "Constanta",
            "Bucharest",
            "Buchar",
            "Buchare",
            "Buchat",
            "Bucharst",
            "Buchart"
        )
        cityNames.forEach {
            cities.add(
                City(
                    it,
                    generateRandomString(),
                    generateRandomString(),
                    CityCoordinate(10.0000, 12.000)
                )
            )
        }
    }

    /**
     * Generates a random String
     *
     * @return
     */
    private fun generateRandomString(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z')
        val randomString = (1..10)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
        randomString[0].toUpperCase()
        return randomString
    }
}