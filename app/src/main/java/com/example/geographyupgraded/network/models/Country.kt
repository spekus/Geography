package com.example.geographyupgraded.network.models

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

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
) : ViewModel