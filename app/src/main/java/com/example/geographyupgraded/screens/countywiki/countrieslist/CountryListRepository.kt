package com.example.geographyupgraded.screens.countywiki.countrieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.geographyupgraded.database.CountriesDatabase
import com.example.geographyupgraded.database.CountryEntity
import com.example.geographyupgraded.network.CountryApiStatus
import com.example.geographyupgraded.network.Network
import com.example.geographyupgraded.network.models.asCountryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CountryListRepository(private val database: CountriesDatabase) {
    val _apiCallStatus = MutableLiveData<CountryApiStatus?>()
    val status: LiveData<CountryApiStatus?>
        get() = _apiCallStatus

    private val selectedRegion = MutableLiveData<String>()
    private val isAnyRegionSelected: LiveData<Boolean> = Transformations.map(selectedRegion) {
        !it.isNullOrEmpty()
    }

    private val selectedCountries: LiveData<List<CountryEntity>> =
        Transformations.switchMap(selectedRegion) { regionName ->
            database.countriesDao!!.getCountriesByRegion(regionName)
        }

    private val allCountries: LiveData<List<CountryEntity>> =
        database.countriesDao!!.getAllCountries()

    val countriesToDisplay: LiveData<List<CountryEntity>> =
        Transformations.switchMap(isAnyRegionSelected) { isRegionSelected ->
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

    suspend fun areCountriesNotInitialized(): Boolean {
        return withContext(Dispatchers.IO) {
            database.countriesDao?.getAnyCountry() == null
        }
    }

    suspend fun initializeCountries() {
        _apiCallStatus.value = CountryApiStatus.LOADING
        withContext(Dispatchers.IO) {
            try {
                val countries = Network.devbytes.getCountries().await()
                val countryEntities = countries.map {
                    it.asCountryEntity()
                }.toTypedArray()
                database.countriesDao!!.insertAll(*countryEntities)
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private suspend fun handleError(e :Exception) {
        withContext(Dispatchers.Main) {
            _apiCallStatus.value = CountryApiStatus.ERROR
            Log.e("CountryListRepository", "Api call Failed, ${e.localizedMessage}")
        }
    }
}