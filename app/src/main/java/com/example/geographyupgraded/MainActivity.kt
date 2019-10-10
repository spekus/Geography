package com.example.geographyupgraded

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber.DebugTree
import timber.log.Timber
import androidx.room.RoomDatabase
//import com.example.geographyupgraded.dagger.GeographyApplication
import com.example.geographyupgraded.dagger.AppComponent
import com.example.geographyupgraded.dagger.DaggerAppComponent
import com.example.geographyupgraded.dagger.GeographyApplication
import com.example.geographyupgraded.database.CountriesDatabase
import com.example.geographyupgraded.network.CountryService
import dagger.android.AndroidInjection
//import com.example.geographyupgraded.dagger.DaggerAppComponent
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
//    @Inject
//    lateinit var database: CountryService

        override fun onCreate(savedInstanceState: Bundle?) {
            AndroidInjection.inject(this)
            super.onCreate(savedInstanceState)
            setContentView(R.layout.main_activity)
//            GeographyApplication.buildComponent().inject(this)
//            DaggerAppComponent.create().inject(this)
//            DaggerAppComponent.create().inject(this)
            Timber.plant(DebugTree())
//            val salys = database.getCountries()
//            Timber.e(salys.toString())
//            database.isOpen
        }

}
