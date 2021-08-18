package org.m0skit0.android.dabestmoviedbapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsUseCaseImpl
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.SimilarTVShowUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.similarshows.SimilarTVShowUseCaseImpl
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopRatedTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopRatedTVShowsUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModuleBinder {
    @Binds
    @Singleton
    abstract fun bindTopRatedTVShowsUseCase(useCase: TopRatedTVShowsUseCaseImpl): TopRatedTVShowsUseCase

    @Binds
    @Singleton
    abstract fun bindTVShowDetailsUseCase(useCase: TVShowDetailsUseCaseImpl): TVShowDetailsUseCase

    @Binds
    @Singleton
    abstract fun bindSimilarTVShowUseCase(useCase: SimilarTVShowUseCaseImpl): SimilarTVShowUseCase
}
