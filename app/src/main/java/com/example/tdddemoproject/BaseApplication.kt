package com.example.tdddemoproject

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class BaseApplication : Application() {

    private val viewModelModule = module {
        // TODO Add your view model class :  Ex -> viewModel { SearchViewModel() }
    }

    val trieModule = module {
        // TODO Singleton DI for Trie algorithm class
        // TODO -> single { TrieAlgorithm() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(listOf(viewModelModule, trieModule))
        }
    }

}