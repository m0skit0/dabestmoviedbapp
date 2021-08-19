package org.m0skit0.android.dabestmoviedbapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModel
import org.m0skit0.android.dabestmoviedbapp.presentation.utils.common.ErrorViewModelImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class PresentationModuleBinder {

    @Binds
    abstract fun bindErrorViewModel(implementation: ErrorViewModelImpl): ErrorViewModel
}
