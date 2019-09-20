package com.example.geographyupgraded.screens.countywiki.countrieslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.geographyupgraded.database.asPresentationModel
import com.example.geographyupgraded.database.getDatabase
import com.example.geographyupgraded.screens.countywiki.presentationmodels.CountryPresentationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CountriesListViewModel(application: Application) : AndroidViewModel(application) {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val repository = CountryListRepository(database)

    val countriesToDisplay: LiveData<List<CountryPresentationModel>> =
        Transformations.map(repository.countriesToDisplay) {
            it.asPresentationModel()
        }

    init {
        viewModelScope.launch {
            repository.refreshCountries()
        }
        selectAllCountries()
    }

    fun selectAllCountries() {
        repository.unSelectRegion()
    }

    fun selectRegion(countryName: String) {
        repository.selectRegion(countryName)
    }

    companion object {
        val EUROPE_REGION = "Europe"
        val AFRICA_REGION = "Africa"
        val AMERICAS_REGION = "Americas"
        val OCEANIA_REGION = "Oceania"
    }
}
