package org.m0skit0.android.dabestmoviedbapp.data.showdetails

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState

typealias TVShowDetailsRepository = suspend (state: ApplicationState) -> Either<Throwable, TVShowDetailsState>
typealias TVShowDetailsState = Pair<ApplicationState, TVShowDetailsData>

suspend fun tvShowDetails(
    state: ApplicationState,
    tvShowDetailsService: TVShowDetailsService = koin().get(),
): Either<Throwable, TVShowDetailsState> = Either.catch {
    state to tvShowDetailsService.tvShowDetails(state.currentShowId).toTVShowDetailsData()
}
