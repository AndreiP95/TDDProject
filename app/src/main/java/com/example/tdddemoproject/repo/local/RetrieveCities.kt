package com.example.tdddemoproject.repo.local

import androidx.fragment.app.Fragment
import com.example.tdddemoproject.R
import com.example.tdddemoproject.repo.model.City
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.InputStream

class RetrieveCities {

    fun loadCities(context: Fragment): ArrayList<City>? {
        val reader: InputStream = context.resources.openRawResource(R.raw.cities)
        val buffer: ByteArray? = ByteArray(reader.available())
        while (reader.read(buffer) != -1) {
        }
        var text: String? = null
        buffer?.let {
            text = String(it)
        }
        val cityJsonList = ObjectMapper().readValue<ArrayList<City>>(
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