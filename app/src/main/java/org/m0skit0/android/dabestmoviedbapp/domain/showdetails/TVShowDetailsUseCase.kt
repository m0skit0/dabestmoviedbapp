package org.m0skit0.android.dabestmoviedbapp.domain.showdetails

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepository
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext

suspend fun tvShowDetails(
    id: Long,
    repository: TVShowDetailsRepository = koin().get(),
): Either<Throwable, TVShowDetailsDomain> = withUseCaseContext { repository(id).map { it.toTVShowDetailsDomain() } }
