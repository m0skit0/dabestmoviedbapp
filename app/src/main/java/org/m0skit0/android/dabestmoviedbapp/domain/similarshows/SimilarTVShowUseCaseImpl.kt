package org.m0skit0.android.dabestmoviedbapp.domain.similarshows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowsRepository
import javax.inject.Inject
import javax.inject.Named

// TODO Unit test
class SimilarTVShowUseCaseImpl @Inject constructor(
    @Named(BuildConfig.NAMED_SIMILAR_TV_SHOWS_REPOSITORY)
    private val tvShowsRepository: SimilarTVShowsRepository
) : SimilarTVShowUseCase {

    override suspend fun similarTVShows(id: Long): List<SimilarTVShowsDomain> =
        withContext(Dispatchers.IO) {
            tvShowsRepository.similarTVShows(id).map { it.toSimilarTVShowsDomain() }
        }
}
