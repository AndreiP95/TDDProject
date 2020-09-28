package com.example.tdddemoproject.utils.trie

import com.example.tdddemoproject.repo.model.City
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Trie class that contains all logic regarding adding and finding Cities
 *
 * @property city - city to add in trie
 * @property value - city name that is parsed inside the Trie
 */
class Trie(private var city: City?, private var value: String?) {
    private val children: MutableMap<Char, Trie>
    private var terminal = false

    /**
     * Secondary constructor
     */
    constructor(): this(null, null)

    /**
     * Inserting city inside Trie
     *
     * @param city - City that will be inserted
     */
    fun insert(city: City?) {
        requireNotNull(city?.name) { "Cannot add null to a Trie" }
        var node: Trie? = this
        for (c in city?.name.toString().toCharArray()) {
            if (node != null) {
                if (!node.children.containsKey(c)) {
                    node.add(c, city)
                }
                node = node.children[c]
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
    fun findCitiesWith(prefix: String): ArrayList<City?> {
        var node: Trie? = this
        for (c in prefix.toCharArray()) {
            if (node != null && !node.children.containsKey(c)) {
                return emptyList<City?>() as ArrayList<City?>
            }
            if (node != null) {
                node = node.children[c]
            }
        }
        return node?.allPrefixes() ?: arrayListOf()
    }

    /**
     * Recursive method that finds in childs the nodes that contain the given prefixes
     *
     * @return - The ArrayList of cities that contains the prefixes
     */
    private fun allPrefixes(): ArrayList<City?> {
        val results: ArrayList<City?> = ArrayList()
        if (terminal) {
            results.add(city)
        }
        for ((_, child) in children) {
            val childPrefixes = child.allPrefixes()
            results.addAll(childPrefixes)
        }
        return results
    }

    init {
        children = HashMap()
    }
}
