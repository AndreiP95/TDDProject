package com.example.tdddemoproject.searchAlgorithmTests

import com.example.tdddemoproject.model.City
import com.example.tdddemoproject.model.Coodrinates
import kotlin.random.Random

object MockedCityList {
    fun getCities(): ArrayList<City> {
        val cities: ArrayList<City> = arrayListOf()
        for(i in 0..15){
            cities.add(City(generateRandomString(), generateRandomString(), generateRandomString(),
                Coodrinates(10.0000,12.000)
            ))
        }
        cities.add(City(generateRandomString(), "Citroen", generateRandomString(), Coodrinates(10.000, 10.000)))
        cities.add(City(generateRandomString(),"Bucharest", generateRandomString(), Coodrinates(10.000, 10.000)))

        return cities
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