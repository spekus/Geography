package com.example.geographyupgraded.screens.countywiki.countrieslist

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.example.geographyupgraded.database.CountriesRepository
import com.example.geographyupgraded.database.getDatabase
import com.google.android.material.chip.Chip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CountriesListViewModel(application: Application) : AndroidViewModel(application) {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
     val countriesRepository = CountriesRepository(database)

    val allCountries = countriesRepository.countries
    val selectedCountries = countriesRepository.selectedCountries
    val isRegionSelected = countriesRepository.isAnyRegionSelected

    val countriesToShow = Transformations.switchMap(isRegionSelected){isRegionSelected->
        when(isRegionSelected){
            true -> selectedCountries
            else -> allCountries
        }
    }

    init{
        viewModelScope.launch {
            countriesRepository.refreshCountries()
        }
        resetToAllCountries()
    }

    fun resetToAllCountries(){
        countriesRepository.resetRegion()
    }

    fun selectRegion(view : View, countryName : String){
        val viewas = view as Chip

            countriesRepository.selectRegion(countryName)
    }

    companion object{
        val EUROPE_REGION = "Europe"
    }
}
