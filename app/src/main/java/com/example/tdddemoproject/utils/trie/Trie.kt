package com.example.tdddemoproject.utils.trie

import com.example.tdddemoproject.repo.model.City

/**
 * Trie class that contains all logic regarding adding and finding Cities
 *
 * @property city - city to add in trie
 * @property value - city name that is parsed inside the Trie
 */
class Trie(private var city: City? = null, private var value: String? = null) {
    private val children: MutableMap<Char, Trie> by lazy { HashMap() }
    private var terminal = false

    /**
     * Populates the instantiated reference of the Trie
     *
     * @param cities - ArrayList of cities
     */
    fun populateTrie(cities: List<City>) {
        cities.forEach { this.insert(it) }
    }

    /**
     * Inserting city inside Trie
     *
     * @param city - City that will be inserted
     */
    private fun insert(city: City?) {
        requireNotNull(city?.name) { "Cannot add null to a Trie" }
        var node: Trie? = this
        for (c in city?.name.toString().toCharArray()) {
            if (node != null) {
                val charToAdd = c.toLowerCase()
                if (!node.children.containsKey(charToAdd)) {
                    node.add(charToAdd, city)
                }
                node = node.children[charToAdd]
            }
        }
        if (node != null) {
            node.terminal = true
        }
    }

    /**
     * Adds inside Trie by char
     *
     * @param c
     * @param city
     */
    private fun add(c: Char, city: City?) {
        val valueToAdd: String = if (value == null) {
            c.toString()
        } else {
            value + c
        }
        children[c] = Trie(city, valueToAdd)
    }

    /**
     * Method that is called inside search algorithm
     *
     * @param prefix - Input that will be searched
     * @return - ArrayList of found cities with names that match the given prefix
     */
    fun findCitiesWith(prefix: String): ArrayList<City>? {
        var node: Trie? = this
        for (c in prefix.toCharArray()) {
            val charToFind = c.toLowerCase()
            if (node != null && !node.children.containsKey(charToFind)) {
                return arrayListOf()
            }
            if (node != null) {
                node = node.children[charToFind]
            }
        }
        return node?.allPrefixes() ?: arrayListOf()
    }

    /**
     * Recursive method that finds in children the nodes that contain the given prefixes
     *
     * @return - The ArrayList of cities that contains the prefixes
     */
    private fun allPrefixes(): ArrayList<City>? {
        val results: ArrayList<City>? = ArrayList()
        if (terminal) {
            city?.let { results?.add(it) }
        }
        for ((_, child) in children) {
            val childPrefixes = child.allPrefixes()
            if (childPrefixes != null) {
                results?.addAll(childPrefixes)
            }
        }
        return results
    }
}
