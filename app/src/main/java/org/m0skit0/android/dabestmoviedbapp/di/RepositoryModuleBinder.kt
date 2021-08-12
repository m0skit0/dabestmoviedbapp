package org.m0skit0.android.dabestmoviedbapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.m0skit0.android.dabestmoviedbapp.data.*
import javax.inject.Named
import javax.inject.Singleton

const val NAMED_TOP_RATED_TV_SHOWS_REPOSITORY_MOCK = "NAMED_TOP_RATED_TV_SHOWS_REPOSITORY_MOCK"
const val NAMED_TOP_RATED_TV_SHOWS_REPOSITORY_REAL = "NAMED_TOP_RATED_TV_SHOWS_REPOSITORY_REAL"

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModuleBinder {
    @Binds
    @Singleton
    @Named(NAMED_TOP_RATED_TV_SHOWS_REPOSITORY_REAL)
    abstract fun bindTopRatedTVShowsRepository(repository: TopRatedTVShowsRepositoryImpl): TopRatedTVShowsRepository

    @Binds
    @Singleton
    @Named(NAMED_TOP_RATED_TV_SHOWS_REPOSITORY_MOCK)
    abstract fun bindTopRatedTVShowsRepositoryMock(repository: TopRatedTVShowsRepositoryMock): TopRatedTVShowsRepository

    @Binds
    @Singleton
    abstract fun bindTVGenreMapper(mapper: TVGenreMapperImpl): TVGenreMapper
}
