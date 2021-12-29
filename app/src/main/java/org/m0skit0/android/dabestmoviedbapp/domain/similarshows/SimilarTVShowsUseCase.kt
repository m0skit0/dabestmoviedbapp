package org.m0skit0.android.dabestmoviedbapp.domain.similarshows

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.applicationState
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.similarTVShowData
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_SIMILAR_TV_SHOWS_REPOSITORY
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState

typealias SimilarTVShowsUseCase = suspend (state: ApplicationState) -> Either<Throwable, SimilarTVShowsUseCaseState>
typealias SimilarTVShowsUseCaseState = Pair<ApplicationState, List<Long>>

val SimilarTVShowsUseCaseState.applicationState: ApplicationState get() = first
val SimilarTVShowsUseCaseState.similarTVShowIds: List<Long> get() = second

suspend fun similarTVShowsUseCase(
    state: ApplicationState,
    repository: SimilarTVShowsRepository = koin().get(NAMED_SIMILAR_TV_SHOWS_REPOSITORY)
): Either<Throwable, SimilarTVShowsUseCaseState> = withUseCaseContext {
    repository(state).map { state -> state.applicationState to (state.similarTVShowData.map { it.id }) }
}
