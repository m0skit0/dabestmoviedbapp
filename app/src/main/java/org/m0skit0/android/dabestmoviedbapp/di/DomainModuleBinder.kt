package org.m0skit0.android.dabestmoviedbapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.SimilarTVShowUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.SimilarTVShowUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModuleBinder {

    @Binds
    @Singleton
    abstract fun bindTVShowDetailsUseCase(useCase: TVShowDetailsUseCaseImpl): TVShowDetailsUseCase

    @Binds
    @Singleton
    abstract fun bindSimilarTVShowUseCase(useCase: SimilarTVShowUseCaseImpl): SimilarTVShowUseCase
}
