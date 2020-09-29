package com.example.tdddemoproject.ui.splashScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tdddemoproject.R
import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.ui.search.SearchCitiesFragment
import com.example.tdddemoproject.utils.searchAlgorithm
import com.example.tdddemoproject.utils.trie.Trie
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.android.synthetic.main.splash_screen_fragment.view.*
import kotlinx.coroutines.*
import java.io.InputStream
import kotlin.coroutines.CoroutineContext


class SplashScreenFragment() : Fragment(), CoroutineScope {
    private lateinit var reader: InputStream
    var cityJsonList: List<City>? = null
    val action = SplashScreenFragmentDirections.actionGoToSearchScreen()
    private var job: Job? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.splash_screen_fragment, container, false)

        // TODO -> Should use return value of loadCities instead of a local variable for the list

        loadCities()

        launch(Dispatchers.Main) {
            delay(2000)
        if(cityJsonList != null){
            initTrieList()
            findNavController().navigate(action)
        } else {
            root.splash_screen_layout.visibility = View.GONE
            root.loading_error_screen_layout.visibility = View.VISIBLE
        }
        }
        return root
    }


    // TODO -> City loading should be on a different Thread
    // TODO -> Move city loading to Repo package.

    private fun loadCities(): List<City>? {
        reader = resources.openRawResource(R.raw.cities)
        var buffer: ByteArray? = ByteArray(reader.available())
        while (reader.read(buffer) != -1) {
        }
        var text: String? = null
        buffer?.let {
            text = String(it)
        }
        // TODO -> list can be saved directly into an ArrayList without the need for conversion
        // TODO -> From
        cityJsonList = ObjectMapper().readValue<List<City>>(
            text,
            ObjectMapper().typeFactory.constructCollectionType(
                List::class.java,
                City::class.java
            )
        )
        text = null
        reader.close()
        return cityJsonList
    }

    fun initTrieList(){
        var list :List<City>  = cityJsonList as List<City>
        var sortedCityList : List<City> =  HashSet<City>(list).sortedBy { it.name }
        var finaList : ArrayList<City>? = null
        for( item in sortedCityList){
            finaList?.add(item)
        }

        var trieList =  finaList?.let { Trie.initTrie(it) }

        trieList?.let { searchAlgorithm(it,"") }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + (job ?: Job())

}