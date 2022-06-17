package org.m0skit0.android.dabestmoviedbapp.domain.toprated

import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TOP_TV_SHOWS_REPOSITORY
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext
import org.m0skit0.android.dabestmoviedbapp.state.TopRatedState

typealias TopTVShowsUseCase = suspend (state: TopRatedState) -> Result<TopRatedState>

suspend fun topTVShowsUseCase(
    state: TopRatedState,
    repository: TopRatedTVShowsRepository = koin().get(NAMED_TOP_TV_SHOWS_REPOSITORY)
): Result<TopRatedState> = withUseCaseContext { repository(state) }
