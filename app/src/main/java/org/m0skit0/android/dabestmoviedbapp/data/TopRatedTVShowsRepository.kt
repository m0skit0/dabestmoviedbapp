package org.m0skit0.android.dabestmoviedbapp.data

import kotlinx.coroutines.flow.Flow

interface TopRatedTVShowsRepository {
    fun topRatedTVShows(): Flow<List<TVShow>>
}
