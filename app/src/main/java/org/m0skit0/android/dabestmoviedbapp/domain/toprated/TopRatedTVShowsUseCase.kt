package org.m0skit0.android.dabestmoviedbapp.domain.toprated

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsRepositoryState
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TOP_TV_SHOWS_REPOSITORY
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState

typealias TopTVShowsUseCase = suspend (state: ApplicationState) -> Either<Throwable, TopRatedTVShowsRepositoryState>

suspend fun topTVShowsUseCase(
    state: ApplicationState,
    repository: TopRatedTVShowsRepository = koin().get(NAMED_TOP_TV_SHOWS_REPOSITORY)
): Either<Throwable, TopRatedTVShowsRepositoryState> = withUseCaseContext { repository(state) }
