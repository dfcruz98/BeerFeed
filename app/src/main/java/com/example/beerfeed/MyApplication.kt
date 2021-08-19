package com.example.beerfeed

import android.app.Application
import com.example.beerfeed.di.databaseModule
import com.example.beerfeed.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                databaseModule,
                networkModule
            )
        }
    }
}