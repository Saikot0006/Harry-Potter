package com.example.harrypotter.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HarryPorterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}