package com.example.geographyupgraded.screens.countywiki

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.RoomDatabase
import com.example.geographyupgraded.dagger.DaggerAppComponent
import com.example.geographyupgraded.database.CountriesDatabase
import com.example.geographyupgraded.database.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

open class BaseViewModel(
                         application: Application

                         ) : AndroidViewModel(application){
//    @Inject
//    lateinit var database: CountriesDatabase

//    init {
////        DaggerAppComponent.create().injectViewModel(this)
//    }

//    DaggerApp

    private val viewModelJob = SupervisorJob()
    val database : CountriesDatabase = getDatabase(application)
    val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
}