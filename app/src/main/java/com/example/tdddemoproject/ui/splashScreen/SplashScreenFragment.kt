package com.example.tdddemoproject.ui.splashScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tdddemoproject.R
import com.example.tdddemoproject.repo.local.RetrieveCities
import kotlinx.android.synthetic.main.splash_screen_fragment.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext


class SplashScreenFragment : Fragment(), CoroutineScope {
    private val action = SplashScreenFragmentDirections.actionGoToSearchScreen()
    private var job: Job? = null
    private val retrieveCities by inject<RetrieveCities>()
    private lateinit var root: View
    private val splashViewModel by viewModel<SplashScreenViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.splash_screen_fragment, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readData()
    }

    private fun showErrorScreen() {
        root.splash_screen_layout.visibility = View.GONE
        root.loading_error_screen_layout.visibility = View.VISIBLE
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + (job ?: Job())


    private fun readData() {
        launch(Dispatchers.IO) {
            val cities = retrieveCities.loadCities(this@SplashScreenFragment)
            if (cities.isNullOrEmpty())
                launch(Dispatchers.Main) { showErrorScreen() }
            else {
                splashViewModel.initTrieList(cities)
                launch(Dispatchers.Main) { findNavController().navigate(action) }
            }
        }
    }

}