package com.example.tdddemoproject

import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.repo.model.CityCoordinate

class MockCityList {
    fun cityList(): List<City> {
        
        val cityList = ArrayList<City>()
        cityList.add(City("Bucharest", "Ro", "1", CityCoordinate()))
        cityList.add(City("Braila", "Ro", "1", CityCoordinate()))
        cityList.add(City("Buzau", "Ro", "1", CityCoordinate()))
        cityList.add(City("Galati", "US", "1", CityCoordinate()))
        cityList.add(City("New york", "Ch", "1", CityCoordinate()))
        cityList.add(City("Shanghai", "Ro", "1", CityCoordinate()))
        cityList.add(City("Amsterdam", "DE", "1", CityCoordinate()))
        cityList.add(City("Paris", "NL", "1", CityCoordinate()))
        cityList.add(City("London", "Ro", "1", CityCoordinate()))
        cityList.add(City("Washington", "Ro", "1", CityCoordinate()))
        cityList.add(City("Jakarta", "Id", "1", CityCoordinate()))
        cityList.add(City("Sofia", "Bg", "1", CityCoordinate()))
        cityList.add(City("Sofia", "Bg", "1", CityCoordinate()))

        return cityList

    }
}