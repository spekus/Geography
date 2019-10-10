package com.example.geographyupgraded.dagger

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.geographyupgraded.MainActivity
import com.example.geographyupgraded.database.CountriesDatabase
import com.example.geographyupgraded.network.BASE_URL
import com.example.geographyupgraded.network.CountryService
import com.example.geographyupgraded.network.Network
import com.example.geographyupgraded.screens.countywiki.country.CountryProfileFragment
import com.example.geographyupgraded.screens.countywiki.country.CountryRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import okhttp3.OkHttpClient
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }
}

@Module
class RepoModule {

    @Provides
    @NotNull
//    @Singleton
    fun provideDataBase(context: Context): CountriesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CountriesDatabase::class.java,
            "allCountries"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
//        return getDatabase(context)
    }

    @Provides
    @NotNull
    fun provideRepo(database: CountriesDatabase): CountryRepository {
        return CountryRepository(database)
    }


}

@Module
class NetworkModule {
    @Provides
//    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
//    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()


    @Provides
//    @Singleton
    fun provideService(retrofit: Retrofit): CountryService {
        return retrofit.create(CountryService::class.java)
    }

}


@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): CountryProfileFragment
}