package com.example.geographyupgraded.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CountryDao {
    @Query("SELECT * from countries_table")
    fun getCountries(): LiveData<List<CountryEntity>>

    @Query("SELECT * from countries_table where shortName = :name")
    fun getCountry(name: String): LiveData<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg countries: CountryEntity)
}


@Database(entities = [CountryEntity::class], version = 5)
abstract class CountriesDatabase : RoomDatabase() {
    abstract val countriesDao: CountryDao
}

private lateinit var INSTANCE: CountriesDatabase

fun getDatabase(context: Context): CountriesDatabase {
    synchronized(CountriesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CountriesDatabase::class.java,
                "countries"
            ).fallbackToDestructiveMigration().build()
        }
    }
    return INSTANCE
}

