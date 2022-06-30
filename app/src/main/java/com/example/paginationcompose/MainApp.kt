package com.example.paginationcompose

import android.app.Application
import com.example.paginationcompose.di.networkModule
import com.example.paginationcompose.di.repositoryModule
import com.example.paginationcompose.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApp)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}