package com.example.geographyupgraded.screens.countywiki.countrieslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.geographyupgraded.database.CountriesRepository
import com.example.geographyupgraded.database.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CountriesListViewModel(application: Application) : AndroidViewModel(application) {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val countriesRepository = CountriesRepository(database)

    val countries = countriesRepository.countries

    init{
        viewModelScope.launch {
            countriesRepository.refreshCountries()
        }
    }
}
