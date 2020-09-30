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

    @Mock
    val fragment = SearchCitiesFragment()

    @Before
    fun setup() {
        fragm = spy(SearchCitiesFragment::class.java)
    }

    @Test
    fun checkShowCityError() {
        val cityList = arrayListOf<City>()
        fragm.validateCityList(cityList, "abc")
        Mockito.verify(fragm, times(1)).showCityError("abc")
    }

    @Test
    fun checkHideCityError() {
        val cityList = arrayListOf<City>()
        cityList.add(City("aa", "bb"))
        Mockito.verify(fragm, times(1)).hideCityError()
    }

    @Test
    fun checkShowRecyclerView() {
        val cityList = arrayListOf<City>()
        fragm.validateCityList(cityList, "abc")
        Mockito.verify(fragm, times(1)).hideRecyclerViewCities()
    }

    @Test
    fun checkHideRecyclerView() {
        val cityList = arrayListOf<City>()
        cityList.add(City("aa", "bb"))
        fragm.validateCityList(cityList, "abc")
        Mockito.verify(fragm, times(1)).showRecyclerViewCities()
    }

}