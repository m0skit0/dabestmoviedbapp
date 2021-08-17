package org.m0skit0.android.dabestmoviedbapp.domain

interface TopRatedTVShowsUseCase {
    suspend fun topTVShows(page: Int = 1): List<TopRatedTVShowDomain>
}
