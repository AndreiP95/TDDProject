package com.example.tdddemoproject.ui.splashScreen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tdddemoproject.R
import com.example.tdddemoproject.repo.model.City
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.android.synthetic.main.splash_screen_fragment.view.*
import java.io.InputStream

class SplashScreenFragment : Fragment() {
    private lateinit var reader: InputStream
    var cityJsonList : List<City>? = null

    companion object {
        fun newInstance() = SplashScreenFragment()
    }

    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.splash_screen_fragment, container, false)

        startReader()
        if(cityJsonList != null){
            var list :List<City>  = cityJsonList as List<City>
            var sortedCityList : List<City> =  HashSet<City>(list).sortedBy { it.name }


        }else{
            root.splash_screen_layout.visibility = View.GONE
            root.loading_error_screen_layout.visibility = View.VISIBLE
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }


    private fun startReader(): List<City>? {
        reader = resources.openRawResource(R.raw.cities)
        var buffer: ByteArray? = ByteArray(reader.available())
        while (reader.read(buffer) != -1) {
        }
        var text: String? = null
        buffer?.let {
            text = String(it)
        }
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

}