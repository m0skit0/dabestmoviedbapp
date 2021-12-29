package org.m0skit0.android.dabestmoviedbapp.domain.showdetails

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepository
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TV_SHOW_DETAILS_REPOSITORY
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState

typealias TVShowDetailsUseCase = suspend (state: ApplicationState) -> Either<Throwable, ApplicationState>

suspend fun tvShowDetailsUseCase(
    state: ApplicationState,
    repository: TVShowDetailsRepository = koin().get(NAMED_TV_SHOW_DETAILS_REPOSITORY),
): Either<Throwable, ApplicationState> = withUseCaseContext { repository(state) }
