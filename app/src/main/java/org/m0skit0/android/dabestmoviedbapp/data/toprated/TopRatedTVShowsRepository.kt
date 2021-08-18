package org.m0skit0.android.dabestmoviedbapp.data.toprated

interface TopRatedTVShowsRepository {
    suspend fun topRatedTVShows(page: Int = 1): List<TopRatedTVShowData>
}
