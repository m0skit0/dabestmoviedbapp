package org.m0skit0.android.dabestmoviedbapp.domain.similarshows

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.similarshows.SimilarTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_SIMILAR_TV_SHOWS_REPOSITORY
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext

typealias SimilarTVShowsUseCase = suspend (id: Long) -> Either<Throwable, List<Long>>

suspend fun similarTVShowsUseCase(
    id: Long,
    repository: SimilarTVShowsRepository = koin().get(NAMED_SIMILAR_TV_SHOWS_REPOSITORY)
): Either<Throwable, List<Long>> = withUseCaseContext { repository(id).map { list -> list.map { it.id } } }
