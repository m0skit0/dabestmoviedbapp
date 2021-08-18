package org.m0skit0.android.dabestmoviedbapp.domain.similarshows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowsRepository
import javax.inject.Inject

class SimilarTVShowUseCaseImpl @Inject constructor(
    private val tvShowsRepository: SimilarTVShowsRepository
) : SimilarTVShowUseCase {

    override suspend fun similarTVShows(id: Long): List<SimilarTVShowsDomain> =
        withContext(Dispatchers.IO) {
            tvShowsRepository.similarTVShows(id).map { it.toSimilarTVShowsDomain() }
        }
}
