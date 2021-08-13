package org.m0skit0.android.dabestmoviedbapp.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.TopRatedTVShowsRepository
import javax.inject.Inject
import javax.inject.Named

class TopRatedTVShowsUseCaseImpl
@Inject constructor(
    @Named(BuildConfig.NAMED_TOP_RATED_TV_SHOWS_REPOSITORY)
    private val topRatedTVShowsRepository: TopRatedTVShowsRepository
) : TopRatedTVShowsUseCase {

    override fun topTVShows(): Flow<List<TVShowDomain>> =
        topRatedTVShowsRepository.topRatedTVShows().map { list -> list.map { it.toTVShowDomain() } }

}
