package org.m0skit0.android.dabestmoviedbapp.data

interface TopRatedTVShowsRepository {
    suspend fun topRatedTVShows(page: Int = 1): List<TopRatedTVShowData>
}
