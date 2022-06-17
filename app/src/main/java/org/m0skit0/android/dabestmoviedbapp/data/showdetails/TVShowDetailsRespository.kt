package org.m0skit0.android.dabestmoviedbapp.data.showdetails

import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.state.ShowDetailsState
import org.m0skit0.android.dabestmoviedbapp.state.updateTvShowDetailsWith

typealias TVShowDetailsRepository = suspend (state: ShowDetailsState) -> Result<ShowDetailsState>

suspend fun tvShowDetails(
    state: ShowDetailsState,
    tvShowDetailsService: TVShowDetailsService = koin().get(),
): Result<ShowDetailsState> = runCatching {
    tvShowDetailsService
        .tvShowDetails(state.currentShowId)
        .toTVShowDetailsData()
        .let {
            state updateTvShowDetailsWith it
        }
}
