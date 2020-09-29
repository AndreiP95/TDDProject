package com.example.tdddemoproject.splashScreen

import com.example.tdddemoproject.MockCityList
import com.example.tdddemoproject.repo.model.City
import junit.framework.Assert
import org.junit.Before
import org.junit.Test

class SplashScreenViewModelTest {
    private lateinit var cityList: List<City>

    @Before
    fun  createCityList(){
        cityList = MockCityList().cityList()
    }

    @Test
    fun checkIfListIsSorted() {
        Assert.assertTrue(cityList.sortedBy {
            it.name
        }.let {
            it[0].name == "Amsterdam" && it[1].name == "Braila"
        })
    }

    @Test
    fun checkForDuplicates(){
        var list :List<City>  = cityList
        var set : Set<City> =  HashSet<City>(list)

        if(set.size < list.size){
            /* There are duplicates */
        }
    }




    @Test
    fun checkLoadingStatus(){


    }

}