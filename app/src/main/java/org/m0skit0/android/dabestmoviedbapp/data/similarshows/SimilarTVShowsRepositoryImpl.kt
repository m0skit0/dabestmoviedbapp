package org.m0skit0.android.dabestmoviedbapp.data.similarshows

import org.m0skit0.android.dabestmoviedbapp.data.retrofit.SimilarTVShowsService
import javax.inject.Inject

// TODO Unit test
class SimilarTVShowsRepositoryImpl @Inject constructor(
    private val similarTVShowsService: SimilarTVShowsService
): SimilarTVShowsRepository {
    override suspend fun similarTVShows(id: Long): List<SimilarTVShowData> =
        similarTVShowsService
            .similarTVShows(id = id)
            .results
            .map { it.toSimilarTVShowsData() }
}
