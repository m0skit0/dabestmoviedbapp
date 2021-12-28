package org.m0skit0.android.dabestmoviedbapp.data.showdetails

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState

typealias TVShowDetailsRepository = suspend (state: ApplicationState) -> Either<Throwable, TVShowDetailsRepositoryState>
typealias TVShowDetailsRepositoryState = Pair<ApplicationState, TVShowDetailsData>

suspend fun tvShowDetails(
    state: ApplicationState,
    tvShowDetailsService: TVShowDetailsService = koin().get(),
): Either<Throwable, TVShowDetailsRepositoryState> = Either.catch {
    state to tvShowDetailsService.tvShowDetails(state.showDetailsState.currentShowId).toTVShowDetailsData()
}
