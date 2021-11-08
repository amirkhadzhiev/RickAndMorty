package com.amir.rickandmorty

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.amir.rickandmorty.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(koinModules)
        }
    }
}