package com.example.geographyupgraded.screens.countywiki.country

import android.app.Application
import androidx.lifecycle.*
import com.example.geographyupgraded.database.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import androidx.lifecycle.Transformations
import androidx.lifecycle.LiveData
import com.example.geographyupgraded.database.asPresentationModel
import com.example.geographyupgraded.screens.countywiki.BaseViewModel
import com.example.geographyupgraded.screens.countywiki.CountryPresentationModel

class CountryProfileViewModel(application: Application) : BaseViewModel(application) {
    private val repository = CountryRepository(database)

    val countryLiveData : LiveData<CountryPresentationModel> = Transformations.map(repository.countryEntity){
        countryEntity -> countryEntity.asPresentationModel()
    }

    fun loadCountry(country: String) {
        viewModelScope.launch {
            repository.selectCountry(country)
        }
    }
}
