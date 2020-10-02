package com.example.tdddemoproject.searchcities

import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.ui.search.SearchCitiesFragment
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*

class SearchCitiesUITest {

    private lateinit var fragm: SearchCitiesFragment

    @Before
    fun setup() {
        fragm = spy(SearchCitiesFragment::class.java)
    }

    /**
     * If the cityList is null or empty -> it should display an error for the city not fount
     */
    @Test
    fun checkShowCityError() {
        val cityList = arrayListOf<City>()
        fragm.validateCityList(cityList, "abc")
        Mockito.verify(fragm, times(1)).showCityError("abc")
    }

    /**
     * If the cityList has elements -> it should hide the error
     */

    @Test
    fun checkHideCityError() {
        val cityList = arrayListOf<City>()
        cityList.add(City("aa", "bb"))
        Mockito.verify(fragm, times(1)).hideCityError()
    }

    /**
     * If the cityList has elements -> it should show the list
     */

    @Test
    fun checkShowRecyclerView() {
        val cityList = arrayListOf<City>()
        fragm.validateCityList(cityList, "abc")
        Mockito.verify(fragm, times(1)).hideRecyclerViewCities()
    }

    /**
     * If the cityList is null or empty -> it should hide the list
     */

    @Test
    fun checkHideRecyclerView() {
        val cityList = arrayListOf<City>()
        cityList.add(City("aa", "bb"))
        fragm.validateCityList(cityList, "abc")
        Mockito.verify(fragm, times(1)).showRecyclerViewCities()
    }

}