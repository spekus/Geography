package com.example.geographyupgraded.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.geographyupgraded.network.models.Country
import com.example.geographyupgraded.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountriesRepository(private val database : CountriesDatabase) {

    val countries : LiveData<List<Country>> = Transformations.map(database.countriesDao.getCountries()) {
        it.map {
            Country(
                region = it.region,
                name = it.name,
                capital = it.capital
            )
        }
    }

     fun loadCountry(countryName : String) : LiveData<CountryEntity>{
              return database.countriesDao.getCountry(countryName)
    }

    suspend fun refreshCountries(){
        withContext(Dispatchers.IO){
            val countries = Network.devbytes.getCountries().await()
            val countryEntities = countries.map {
                CountryEntity(
                    shortName = it.name,
                    name = it.name,
                    region = it.region ?: "",
                    capital = it.capital ?: "",
                    subregion = it.subregion ?: "",
                    population = it.population ?: 0L,
                    area = it.area ?: 0F,
                    gini = it.gini ?: 0F,
                    countryFlagUrl = "https://www.countryflags.io/${it?.alpha2Code ?: ""}/shiny/64.png"
                )
            }.toTypedArray()
            database.countriesDao.insertAll(*countryEntities)
        }
    }

}