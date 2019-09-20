package com.example.geographyupgraded.screens.countywiki

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

data class CountryPresentationModel (
    val alpha2Code: String = "",
    val name: String = "",
    val capital: String? = "",
    val region: String? = "",
    val subregion: String? = "",
    val currency: String? = "",
    val population: Long? = 0,
    val area: Float? = 0f,
    val gini: Float? = 0f,
    val countryFlagUrl: String? = null
) : ViewModel