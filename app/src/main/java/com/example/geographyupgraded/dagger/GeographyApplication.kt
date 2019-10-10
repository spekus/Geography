package com.example.geographyupgraded.dagger

import android.app.Application
import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class GeographyApplication : Application(), HasAndroidInjector {
    @Inject
//    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var appComponent: AppComponent


//    override fun androidInjector(): AndroidInjector<Any> {
//        return dispatchingAndroidInjector
//    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

//     lateinit var component : AppComponent


//    override fun getApplicationContext(): Context {
////        return super.getApplicationContext()
////    }

    override fun onCreate() {
        super.onCreate()
//        DaggerAppComponent.create().inject(this)
        appComponent = DaggerAppComponent.builder()
            .applicationBind(this)
            .build()
        appComponent.inject(this)
    }

//    fun buildComponent(): AppComponent {
//        return DaggerAppComponent.builder()
////        .appModule(AppModule(this))
//        .build();
//    }
//
//    fun getAppComponent() : AppComponent {
//        return component
//    }
}
