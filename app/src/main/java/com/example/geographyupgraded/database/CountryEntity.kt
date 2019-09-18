package com.example.geographyupgraded.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries_table")
data class CountryEntity (
    @PrimaryKey
    val shortName: String,
    @ColumnInfo(name = "country_name")
    val name: String,
    val region: String,
    val capital: String,
    val subregion: String,
    val population: Long,
    val area: Float,
    val gini: Float,
    val countryFlagUrl : String
)
