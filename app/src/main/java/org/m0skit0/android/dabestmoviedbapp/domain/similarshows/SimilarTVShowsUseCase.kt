package org.m0skit0.android.dabestmoviedbapp.domain.similarshows

import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_SIMILAR_TV_SHOWS_REPOSITORY
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext
import org.m0skit0.android.dabestmoviedbapp.state.SimilarShowsState

typealias SimilarTVShowsUseCase = suspend (state: SimilarShowsState) -> Result<SimilarShowsState>

suspend fun similarTVShowsUseCase(
    state: SimilarShowsState,
    repository: SimilarTVShowsRepository = koin().get(NAMED_SIMILAR_TV_SHOWS_REPOSITORY)
): Result<SimilarShowsState> = withUseCaseContext {
    repository(state).map { newState ->
        newState.copy(similarShows = newState.similarShows.dropWhile { it.id == newState.currentShowId })
    }
}
