package com.example.tdddemoproject.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tdddemoproject.R
import com.example.tdddemoproject.repo.model.City
import com.example.tdddemoproject.ui.search.holder.CityViewHolder
import org.koin.core.KoinComponent

class CityAdapter(private val cities: ArrayList<City>) : RecyclerView.Adapter<CityViewHolder>(), KoinComponent {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val cityView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false)

        return CityViewHolder(cityView)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount(): Int = cities.size

}