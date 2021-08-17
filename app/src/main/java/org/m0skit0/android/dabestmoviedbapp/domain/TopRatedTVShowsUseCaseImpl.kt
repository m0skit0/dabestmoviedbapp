package org.m0skit0.android.dabestmoviedbapp.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.TopRatedTVShowsRepository
import javax.inject.Inject
import javax.inject.Named

class TopRatedTVShowsUseCaseImpl
@Inject constructor(
    @Named(BuildConfig.NAMED_TOP_RATED_TV_SHOWS_REPOSITORY)
    private val topRatedTVShowsRepository: TopRatedTVShowsRepository
) : TopRatedTVShowsUseCase {

    override suspend fun topTVShows(page: Int): List<TVShowDomain> =
        withContext(Dispatchers.IO) {
            topRatedTVShowsRepository.topRatedTVShows().map { it.toTVShowDomain() }
        }
}
