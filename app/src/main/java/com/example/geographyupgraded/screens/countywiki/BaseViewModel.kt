package com.example.geographyupgraded.screens.countywiki

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.geographyupgraded.database.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseViewModel(application: Application) : AndroidViewModel(application){
    private val viewModelJob = SupervisorJob()
    val database = getDatabase(application)
    val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
}