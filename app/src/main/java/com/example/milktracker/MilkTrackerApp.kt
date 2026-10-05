package com.example.milktracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MilkTrackerApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
