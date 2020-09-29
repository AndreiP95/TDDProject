package com.example.tdddemoproject.ui.search.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tdddemoproject.repo.model.City
import kotlinx.android.synthetic.main.item_city.view.*

class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(city: City){
        itemView.tv_name.text = city.name.toString()
        itemView.tv_country.text = city.country.toString()
        itemView.tv_long.text = city.coord?.lon.toString()
        itemView.tv_lat.text = city.coord?.lat.toString()
    }

}