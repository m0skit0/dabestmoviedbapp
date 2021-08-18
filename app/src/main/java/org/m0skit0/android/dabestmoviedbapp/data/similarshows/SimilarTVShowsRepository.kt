package org.m0skit0.android.dabestmoviedbapp.data.similarshows

interface SimilarTVShowsRepository {
    suspend fun similarTVShows(id: Long): List<SimilarTVShowsData>
}
