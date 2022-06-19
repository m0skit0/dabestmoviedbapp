package org.m0skit0.android.dabestmoviedbapp

import android.app.Application
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import org.m0skit0.android.dabestmoviedbapp.di.startKoin

@ExperimentalCoilApi
@ExperimentalPagerApi
class DaBestMovieDBApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }
}
