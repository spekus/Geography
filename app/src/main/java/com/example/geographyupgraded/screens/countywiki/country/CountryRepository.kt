package com.example.geographyupgraded.screens.countywiki.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.geographyupgraded.database.CountriesDatabase
import com.example.geographyupgraded.database.CountryEntity

class CountryRepository(private val database: CountriesDatabase) {

    private val countryNameLiveData = MutableLiveData<String>()

    val countryEntity: LiveData<CountryEntity> =
        Transformations.switchMap(countryNameLiveData) { countryName ->
            getCountry(countryName)
        }
    
    private fun getCountry(countryName: String): LiveData<CountryEntity> {
        return database.countriesDao.getCountry(countryName)
    }

    fun selectCountry(country: String) {
        countryNameLiveData.value = country
    }
}