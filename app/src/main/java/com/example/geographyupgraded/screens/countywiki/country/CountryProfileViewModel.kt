package com.example.geographyupgraded.screens.countywiki.country

import android.app.Application
import androidx.lifecycle.*
import com.example.geographyupgraded.database.CountriesRepository
import com.example.geographyupgraded.database.CountryEntity
import com.example.geographyupgraded.database.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import androidx.lifecycle.Transformations
import androidx.lifecycle.LiveData
import com.example.geographyupgraded.screens.countywiki.countrieslist.CountriesListViewModel

class CountryProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val countriesRepository = CountriesRepository(database)

    private val countryNameLiveData = MutableLiveData<String>()


    var countryLiveData: LiveData<CountryEntity> =
        Transformations.switchMap(countryNameLiveData) { countryName ->
            countriesRepository.loadCountry(countryName)
        }

    fun loadCountry(country: String) {
        viewModelScope.launch {
            countryNameLiveData.value = country
        }
    }
}
