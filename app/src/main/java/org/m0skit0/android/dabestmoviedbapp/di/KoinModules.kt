package org.m0skit0.android.dabestmoviedbapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.m0skit0.android.dabestmoviedbapp.BuildConfig

private lateinit var koinApplication: KoinApplication

fun Application.startKoin() {
    koinApplication = startKoin {
        androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        androidContext(this@startKoin)
        modules(
            retrofitModule,
        )
    }
}

fun koin(): Koin = koinApplication.koin
