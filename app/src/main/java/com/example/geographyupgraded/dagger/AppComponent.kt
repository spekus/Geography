package com.example.geographyupgraded.dagger

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.example.geographyupgraded.MainActivity
import com.example.geographyupgraded.screens.countywiki.BaseViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Component(
    modules = [
//        AndroidSupportInjectionModule ::class,
//        ActivityBindingModule::class
        AppModule::class,
        RepoModule::class,
        NetworkModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class
    ]
)

//interface AppComponent : AndroidInjector<MainActivity>
//@Singleton
//interface AppComponent {
//    //    fun inject (mainActivity: AppCompatActivity)
//    fun injectViewModel(baseViewModel: BaseViewModel)
//}
//@Singleton
//interface AppComponent : AndroidInjector<GeographyApplication>
interface AppComponent {
    fun inject(application: GeographyApplication)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun applicationBind(application: Application): Builder

    }
}