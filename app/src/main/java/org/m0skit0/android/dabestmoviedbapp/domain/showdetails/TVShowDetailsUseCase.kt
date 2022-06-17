package org.m0skit0.android.dabestmoviedbapp.domain.showdetails

import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepository
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TV_SHOW_DETAILS_REPOSITORY
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext
import org.m0skit0.android.dabestmoviedbapp.state.ShowDetailsState

typealias TVShowDetailsUseCase = suspend (state: ShowDetailsState) -> Result<ShowDetailsState>

suspend fun tvShowDetailsUseCase(
    state: ShowDetailsState,
    repository: TVShowDetailsRepository = koin().get(NAMED_TV_SHOW_DETAILS_REPOSITORY),
): Result<ShowDetailsState> = withUseCaseContext { repository(state) }
