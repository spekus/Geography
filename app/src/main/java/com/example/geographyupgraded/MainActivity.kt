package com.example.geographyupgraded

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber.DebugTree
import timber.log.Timber
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.main_activity)
            Timber.plant(DebugTree())
        }
}
