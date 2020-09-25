package com.example.tdddemoproject.utils

import com.example.tdddemoproject.model.City
import com.example.tdddemoproject.model.Coodrinates
import com.example.tdddemoproject.utils.trieImplementation.Trie
import kotlin.random.Random

object GenerateCities {
     fun initCities(cities: ArrayList<City>){
        for(i in 0..200000){
            cities.add(
                City(generateRandomString(), generateRandomString(), generateRandomString(),
                Coodrinates(10.0000,12.000)
            )
            )
        }
        cities.add(City(generateRandomString(), "Citroen", generateRandomString(), Coodrinates(10.000, 10.000)))
        cities.add(City(generateRandomString(),"Bucharest", generateRandomString(), Coodrinates(10.000, 10.000)))
        cities.add(City(generateRandomString(),"Buchar", generateRandomString(), Coodrinates(10.000, 10.000)))
        cities.add(City(generateRandomString(),"Buchare", generateRandomString(), Coodrinates(10.000, 10.000)))
        cities.add(City(generateRandomString(),"Buchat", generateRandomString(), Coodrinates(10.000, 10.000)))
        cities.add(City(generateRandomString(),"Bucharst", generateRandomString(), Coodrinates(10.000, 10.000)))
        cities.add(City(generateRandomString(),"Buchart", generateRandomString(), Coodrinates(10.000, 10.000)))
    }

    fun getCitiesTrie(): Trie {
        val cities: ArrayList<City> = arrayListOf()
        val citiesTrie = Trie()
        initCities(cities)
        for(city in cities){
            citiesTrie.insert(city)
        }
        return citiesTrie
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