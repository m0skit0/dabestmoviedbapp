package org.m0skit0.android.dabestmoviedbapp.data.similarshows

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.SimilarTVShowsService
import org.m0skit0.android.dabestmoviedbapp.di.koin

typealias SimilarTVShowsRepository = suspend (id: Long) -> Either<Throwable, List<SimilarTVShowData>>

suspend fun similarTVShows(
    id: Long,
    similarTVShowsService: SimilarTVShowsService = koin().get()
): Either<Throwable, List<SimilarTVShowData>> = Either.catch {
    similarTVShowsService.similarTVShows(id = id).results.map { it.toSimilarTVShowsData() }
}
