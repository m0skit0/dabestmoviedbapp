package org.m0skit0.android.dabestmoviedbapp.data.similarshows

import javax.inject.Inject

// TODO Unit test
class SimilarTVShowsRepositoryMock @Inject constructor(): SimilarTVShowsRepository {

    private val similarShowsMap = mapOf(
        1L to listOf(
            SimilarTVShowData(2)
        ),
        2L to listOf(
            SimilarTVShowData(1)
        ),
    )

    override suspend fun similarTVShows(id: Long): List<SimilarTVShowData> =
        similarShowsMap.getOrElse(id) { emptyList() }
}
