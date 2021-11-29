package org.m0skit0.android.dabestmoviedbapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepository
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepositoryImpl
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepositoryMock
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowsRepositoryImpl
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowsRepositoryMock
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.TVGenreMapper
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.TVGenreMapperImpl
import javax.inject.Named
import javax.inject.Singleton

const val NAMED_SHOW_DETAILS_REPOSITORY_MOCK = "NAMED_SHOW_DETAILS_REPOSITORY_MOCK"
const val NAMED_SHOW_DETAILS_REPOSITORY_REAL = "NAMED_SHOW_DETAILS_REPOSITORY_REAL"

const val NAMED_SIMILAR_TV_SHOWS_REPOSITORY_MOCK = "NAMED_SIMILAR_TV_SHOWS_REPOSITORY_MOCK"
const val NAMED_SIMILAR_TV_SHOWS_REPOSITORY_REAL = "NAMED_SIMILAR_TV_SHOWS_REPOSITORY_REAL"

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModuleBinder {

    @Binds
    @Singleton
    abstract fun bindTVGenreMapper(mapper: TVGenreMapperImpl): TVGenreMapper

    @Binds
    @Singleton
    @Named(NAMED_SHOW_DETAILS_REPOSITORY_REAL)
    abstract fun bindTVShowDetailsRepository(repository: TVShowDetailsRepositoryImpl): TVShowDetailsRepository

    @Binds
    @Singleton
    @Named(NAMED_SHOW_DETAILS_REPOSITORY_MOCK)
    abstract fun bindTVShowDetailsRepositoryMock(repository: TVShowDetailsRepositoryMock): TVShowDetailsRepository

    @Binds
    @Singleton
    @Named(NAMED_SIMILAR_TV_SHOWS_REPOSITORY_REAL)
    abstract fun bindSimilarTVShowsRepository(repository: SimilarTVShowsRepositoryImpl): SimilarTVShowsRepository

    @Binds
    @Singleton
    @Named(NAMED_SIMILAR_TV_SHOWS_REPOSITORY_MOCK)
    abstract fun bindSimilarTVShowsRepositoryMock(repository: SimilarTVShowsRepositoryMock): SimilarTVShowsRepository
}
