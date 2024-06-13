package com.example.myapppart3

import android.app.Application
import com.example.myapppart3.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()
    }
}