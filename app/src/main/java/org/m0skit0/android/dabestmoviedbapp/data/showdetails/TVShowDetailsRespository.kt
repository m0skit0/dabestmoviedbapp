package org.m0skit0.android.dabestmoviedbapp.data.showdetails

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.state.ShowDetailsState
import org.m0skit0.android.dabestmoviedbapp.state.updateTvShowDetailsWith

typealias TVShowDetailsRepository = suspend (state: ShowDetailsState) -> Either<Throwable, ShowDetailsState>

suspend fun tvShowDetails(
    state: ShowDetailsState,
    tvShowDetailsService: TVShowDetailsService = koin().get(),
): Either<Throwable, ShowDetailsState> = Either.catch {
    tvShowDetailsService
        .tvShowDetails(state.currentShowId)
        .toTVShowDetailsData()
        .let {
            state updateTvShowDetailsWith it
        }
}
