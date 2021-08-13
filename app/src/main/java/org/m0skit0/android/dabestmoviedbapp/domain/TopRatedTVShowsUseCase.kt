package org.m0skit0.android.dabestmoviedbapp.domain

import kotlinx.coroutines.flow.Flow

interface TopRatedTVShowsUseCase {
    fun topTVShows(): Flow<List<TVShowDomain>>
}
