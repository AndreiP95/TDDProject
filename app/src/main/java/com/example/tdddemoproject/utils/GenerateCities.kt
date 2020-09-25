package com.example.tdddemoproject.utils

import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.repo.model.CityCoordinate
import kotlin.random.Random

object GenerateCities {
    fun initCities(cities: ArrayList<City>) {
        for (i in 0..200000) {
            cities.add(
                City(
                    generateRandomString(), generateRandomString(), generateRandomString(),
                    CityCoordinate(10.0000, 12.000)
                )
            )
        }
        cities.add(
            City(
                generateRandomString(),
                "Citroen",
                generateRandomString(),
                CityCoordinate(10.000, 10.000)
            )
        )
        cities.add(
            City(
                generateRandomString(),
                "Bucharest",
                generateRandomString(),
                CityCoordinate(10.000, 10.000)
            )
        )
        cities.add(
            City(
                generateRandomString(),
                "Buchar",
                generateRandomString(),
                CityCoordinate(10.000, 10.000)
            )
        )
        cities.add(
            City(
                generateRandomString(),
                "Buchare",
                generateRandomString(),
                CityCoordinate(10.000, 10.000)
            )
        )
        cities.add(
            City(
                generateRandomString(),
                "Buchat",
                generateRandomString(),
                CityCoordinate(10.000, 10.000)
            )
        )
        cities.add(
            City(
                generateRandomString(),
                "Bucharst",
                generateRandomString(),
                CityCoordinate(10.000, 10.000)
            )
        )
        cities.add(
            City(
                generateRandomString(),
                "Buchart",
                generateRandomString(),
                CityCoordinate(10.000, 10.000)
            )
        )
    }

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