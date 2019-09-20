package com.example.geographyupgraded.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.geographyupgraded.screens.countywiki.presentationmodels.CountryPresentationModel

@Entity(tableName = "countries_table")
data class CountryEntity (
    @PrimaryKey
    val shortName: String,
    @ColumnInfo(name = "country_name")
    val name: String,
    @ColumnInfo(name = "region_name")
    val region: String,
    val capital: String,
    val subregion: String,
    val population: Long,
    val area: Float,
    val gini: Float,
    val countryFlagUrl : String
)

fun CountryEntity.asPresentationModel(): CountryPresentationModel{
    return CountryPresentationModel(
        name = this.name,
        capital = this.capital,
        region = this.capital,
        countryFlagUrl = this.countryFlagUrl,
        gini = this.gini,
        area = this.area,
        population = this.population
    )
}

fun List<CountryEntity>.asPresentationModel(): List<CountryPresentationModel>{
    return this.map { it.asPresentationModel() }
}