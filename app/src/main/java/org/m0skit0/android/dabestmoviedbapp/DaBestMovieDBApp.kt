package org.m0skit0.android.dabestmoviedbapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.m0skit0.android.dabestmoviedbapp.di.startKoin

@HiltAndroidApp
class DaBestMovieDBApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }
}
