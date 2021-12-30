package org.m0skit0.android.dabestmoviedbapp.domain.similarshows

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_SIMILAR_TV_SHOWS_REPOSITORY
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext
import org.m0skit0.android.dabestmoviedbapp.state.SimilarShowsState

typealias SimilarTVShowsUseCase = suspend (state: SimilarShowsState) -> Either<Throwable, SimilarShowsState>

suspend fun similarTVShowsUseCase(
    state: SimilarShowsState,
    repository: SimilarTVShowsRepository = koin().get(NAMED_SIMILAR_TV_SHOWS_REPOSITORY)
): Either<Throwable, SimilarShowsState> = withUseCaseContext { repository(state) }
