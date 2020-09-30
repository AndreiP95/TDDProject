package com.example.tdddemoproject

import android.app.Application
import com.example.tdddemoproject.repo.local.RetrieveCities
import com.example.tdddemoproject.ui.search.SearchCitiesViewModel
import com.example.tdddemoproject.ui.splashScreen.SplashScreenViewModel
import com.example.tdddemoproject.utils.trie.Trie
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class BaseApplication : Application() {

    private val viewModelModule = module {
        viewModel { SearchCitiesViewModel() }
        viewModel { SplashScreenViewModel() }
    }

    val trieModule = module {
        single { Trie() }
    }

    val readCitiesModule = module {
        single { RetrieveCities() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(listOf(viewModelModule, trieModule, readCitiesModule))
        }
    }

}