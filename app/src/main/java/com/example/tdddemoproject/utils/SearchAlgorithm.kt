package com.example.tdddemoproject.utils

import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.utils.trie.Trie

/**
 * Calls a Trie - Prefix Tree - in order to display the cities that
 * contain the input written by the user
 *
 * @param cities - The list that contains all the available cities
 * @param cityToFind - The pattern written by the user
 * @return - A nullable list of cities that match the input written by the user
 */
fun searchAlgorithm(cities: ArrayList<City>, cityToFind: String): ArrayList<City?> {
    val citiesTrie = initTrie(cities)
    return citiesTrie.findCitiesWith(cityToFind)
}

/**
 * Initializes Trie and inserts the list of cities
 *
 * @param cities - The list of cities that will be parsed
 * @return - an initialized Trie
 */
private fun initTrie(cities: ArrayList<City>): Trie {
    val citiesTrie = Trie()
    cities.forEach { citiesTrie.insert(it) }
    return citiesTrie
}

