package com.example.geographyupgraded.screens.countywiki.countrieslist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.geographyupgraded.database.asPresentationModel
import com.example.geographyupgraded.network.CountryApiStatus
import com.example.geographyupgraded.screens.countywiki.BaseViewModel
import com.example.geographyupgraded.screens.countywiki.CountryPresentationModel
import com.example.geographyupgraded.screens.countywiki.country.CountryRepository
import kotlinx.coroutines.launch

class CountriesListViewModel(
    application: Application

) : BaseViewModel(application) {
    private val repository : CountryListRepository = CountryListRepository(database)

    val status: LiveData<CountryApiStatus?> = repository.status

    val countriesToDisplay: LiveData<List<CountryPresentationModel>> =
        Transformations.map(repository.countriesToDisplay) {
            it.asPresentationModel()
        }

    init {
        viewModelScope.launch {
            if (repository.areCountriesNotInitialized()) {
                repository.initializeCountries()
            }
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
