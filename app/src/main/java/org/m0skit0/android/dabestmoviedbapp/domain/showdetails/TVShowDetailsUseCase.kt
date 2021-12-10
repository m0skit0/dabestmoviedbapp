package org.m0skit0.android.dabestmoviedbapp.domain.showdetails

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsData
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepository
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TV_SHOW_DETAILS_REPOSITORY
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext

typealias TVShowDetailsUseCase = suspend (id: Long) -> Either<Throwable, TVShowDetailsData>

suspend fun tvShowDetailsUseCase(
    id: Long,
    repository: TVShowDetailsRepository = koin().get(NAMED_TV_SHOW_DETAILS_REPOSITORY),
): Either<Throwable, TVShowDetailsData> = withUseCaseContext { repository(id) }
