package com.example.geographyupgraded.network.models

import com.example.geographyupgraded.database.CountryEntity

data class Country(
    val alpha2Code: String = "",
    val name: String = "",
    val capital: String? = "",
    val region: String? = "",
    val subregion: String? = "",
    val currency: String? = "",
    val population: Long? = 0,
    val area: Float? = 0f,
    val gini: Float? = 0f,
    val flagUrl: String? = null
)

fun Country.asCountryEntity(): CountryEntity {
    return CountryEntity(
        shortName = this.name,
        name = this.name,
        region = this.region ?: "",
        capital = this.capital ?: "",
        subregion = this.subregion ?: "",
        population = this.population ?: 0L,
        area = this.area ?: 0F,
        gini = this.gini ?: 0F,
        countryFlagUrl = baseUrl + this.alpha2Code + closingUrl
    )
}

fun List<Country>.asCountryEntity(): List<CountryEntity> {
    return this.map { it.asCountryEntity() }
}

const val baseUrl = "https://www.countryflags.io/"
const val closingUrl = "/shiny/64.png"