package org.m0skit0.android.dabestmoviedbapp.data.showdetails

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVShowDetailsService
import org.m0skit0.android.dabestmoviedbapp.di.koin

typealias TVShowDetailsRepository = suspend (id: Long) -> Either<Throwable, TVShowDetailsData>

suspend fun tvShowDetails(
    tvShowDetailsService: TVShowDetailsService = koin().get(),
    id: Long,
): Either<Throwable, TVShowDetailsData> = Either.catch {
    tvShowDetailsService.tvShowDetails(id = id).toTVShowDetailsData()
}
