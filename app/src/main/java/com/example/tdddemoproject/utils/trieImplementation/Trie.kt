package com.example.tdddemoproject.utils.trieImplementation

import android.os.Parcel
import android.os.Parcelable
import com.example.tdddemoproject.repo.model.City

class Trie {

    data class Node(
        var cityName: String? = null,
        var city: City? = null,
        val childNodes: MutableMap<Char, Node> = mutableMapOf()
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            TODO("city"),
            TODO("childNodes")
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(cityName)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Node> {
            override fun createFromParcel(parcel: Parcel): Node {
                return Node(parcel)
            }

            override fun newArray(size: Int): Array<Node?> {
                return arrayOfNulls(size)
            }
        }
    }

    private val root = Node()
    private val foundCities: ArrayList<City> = arrayListOf()

    fun findCitiesThatStartWith(word: String): ArrayList<City> {
        startsWith(word)
        return foundCities
    }

    fun insert(city: City) {
        var currentNode = root
        city.name?.let { cityName ->
            for (char in cityName) {
                if (currentNode.childNodes[char] == null) {
                    currentNode.childNodes[char] = Node()
                }
                currentNode = currentNode.childNodes[char]!!
            }
            currentNode.cityName = cityName
            currentNode.city = city
        }
    }


    private fun startsWith(word: String): Boolean {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                return false
            }
            currentNode = currentNode.childNodes[char]!!
        }
        if (currentNode.cityName != null) {
            currentNode.city?.let { foundCities.add(it) }
        }
        return currentNode.cityName == null
    }


}