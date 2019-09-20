package com.example.geographyupgraded.screens.countywiki.countrieslist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.geographyupgraded.database.asPresentationModel
import com.example.geographyupgraded.screens.countywiki.BaseViewModel
import com.example.geographyupgraded.screens.countywiki.CountryPresentationModel
import kotlinx.coroutines.launch

class CountriesListViewModel(application: Application) : BaseViewModel(application) {
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
