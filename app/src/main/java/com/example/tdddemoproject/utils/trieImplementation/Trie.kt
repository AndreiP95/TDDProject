package com.example.tdddemoproject.utils.trieImplementation

import com.example.tdddemoproject.model.City

class Trie {

    data class Node(
        var cityName: String? = null,
        var city: City? = null,
        val childNodes: MutableMap<Char, Node> = mutableMapOf()
    )

    private val root = Node()
    private val foundCities: ArrayList<City> = arrayListOf()

    fun findCitiesThatStartWith(word: String): ArrayList<City> {
        startsWith(word)
        return foundCities
    }

    fun insert(city: City) {
        var currentNode = root
        for (char in city.name) {
            if (currentNode.childNodes[char] == null) {
                currentNode.childNodes[char] = Node()
            }
            currentNode = currentNode.childNodes[char]!!
        }
        currentNode.cityName = city.name
        currentNode.city = city
    }


    private fun startsWith(word: String): Boolean {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                return false
            }
            currentNode = currentNode.childNodes[char]!!
        }
        if (currentNode.cityName != null){
            currentNode.city?.let { foundCities.add(it) }
        }
        return currentNode.cityName == null
    }


}