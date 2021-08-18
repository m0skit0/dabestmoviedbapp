package org.m0skit0.android.dabestmoviedbapp.domain.similarshows

interface SimilarTVShowUseCase {
    suspend fun similarTVShows(id: Long): List<SimilarTVShowsDomain>
}
