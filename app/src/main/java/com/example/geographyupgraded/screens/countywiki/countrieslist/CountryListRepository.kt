package com.example.geographyupgraded.screens.countywiki.countrieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.geographyupgraded.database.CountriesDatabase
import com.example.geographyupgraded.database.CountryEntity
import com.example.geographyupgraded.network.models.Country
import com.example.geographyupgraded.network.Network
import com.example.geographyupgraded.network.models.asCountryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryListRepository(private val database: CountriesDatabase) {

    private val selectedRegion = MutableLiveData<String>()
    private val isAnyRegionSelected: LiveData<Boolean> = Transformations.map(selectedRegion) {
        !it.isNullOrEmpty()
    }

    private val selectedCountries: LiveData<List<CountryEntity>> =
        Transformations.switchMap(selectedRegion) { regionName ->
            database.countriesDao.getCountriesByRegion(regionName)
        }

    private val allCountries: LiveData<List<CountryEntity>> = database.countriesDao.getAllCountries()

    val countriesToDisplay: LiveData<List<CountryEntity>> = Transformations.switchMap(isAnyRegionSelected) { isRegionSelected ->
        when (isRegionSelected) {
            true -> selectedCountries
            else -> allCountries
        }
    }

    fun selectRegion(regionName: String) {
        selectedRegion.value = regionName
    }

    fun unSelectRegion() {
        selectedRegion.value = ""
    }

    suspend fun refreshCountries() {
        withContext(Dispatchers.IO) {
            val countries = Network.devbytes.getCountries().await()
            val countryEntities = countries.map {
                it.asCountryEntity()
            }.toTypedArray()
            database.countriesDao.insertAll(*countryEntities)
        }
    }
}