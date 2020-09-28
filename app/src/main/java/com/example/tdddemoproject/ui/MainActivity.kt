package com.example.tdddemoproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tdddemoproject.R
import com.example.tdddemoproject.ui.search.SearchCitiesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.container, SearchCitiesFragment(), "Search").commit()
    }
}