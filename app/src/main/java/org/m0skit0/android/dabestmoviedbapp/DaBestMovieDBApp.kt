package org.m0skit0.android.dabestmoviedbapp

import android.app.Application
import org.m0skit0.android.dabestmoviedbapp.di.startKoin

class DaBestMovieDBApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }
}
