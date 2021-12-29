package org.m0skit0.android.dabestmoviedbapp.data.showdetails

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState
import org.m0skit0.android.dabestmoviedbapp.state.updateTvShowDetailsWith

typealias TVShowDetailsRepository = suspend (state: ApplicationState) -> Either<Throwable, ApplicationState>

suspend fun tvShowDetails(
    state: ApplicationState,
    tvShowDetailsService: TVShowDetailsService = koin().get(),
): Either<Throwable, ApplicationState> = Either.catch {
    tvShowDetailsService
        .tvShowDetails(state.showDetailsState.currentShowId)
        .toTVShowDetailsData()
        .let {
            state updateTvShowDetailsWith it
        }
}
