package org.m0skit0.android.dabestmoviedbapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

private lateinit var koinApplication: KoinApplication

fun Application.startKoin() {
    koinApplication = startKoin {
        androidLogger()
        androidContext(this@startKoin)
        modules(
            presentationModule,
            domainModule,
            retrofitModule,
            repositoryModule,
        )
    }
}

fun koin(): Koin = koinApplication.koin
