package com.example.tdddemoproject.utils

import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.utils.trie.Trie

/**
 * Method searches in Trie the input given by the user
 *
 * @param citiesTrie - The initialised Trie that contains all the parsed cities
 * @param cityToFind - The user's input
 * @return - An ArrayList of cities that contains the given input in City Name
 */

fun searchAlgorithm(citiesTrie: Trie, cityToFind: String): ArrayList<City>? {
    return citiesTrie.findCitiesWith(cityToFind)
}
