package org.m0skit0.android.dabestmoviedbapp.domain.toprated

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TOP_TV_SHOWS_REPOSITORY
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext
import org.m0skit0.android.dabestmoviedbapp.state.TopRatedState

typealias TopTVShowsUseCase = suspend (state: TopRatedState) -> Either<Throwable, TopRatedState>

suspend fun topTVShowsUseCase(
    state: TopRatedState,
    repository: TopRatedTVShowsRepository = koin().get(NAMED_TOP_TV_SHOWS_REPOSITORY)
): Either<Throwable, TopRatedState> = withUseCaseContext { repository(state) }
