package org.m0skit0.android.dabestmoviedbapp.domain.toprated

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TOP_TV_SHOWS_REPOSITORY
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext

typealias TopTVShowsUseCase = suspend (page: Int) -> Either<Throwable, List<TopRatedTVShowData>>

suspend fun topTVShowsUseCase(
    page: Int,
    repository: TopRatedTVShowsRepository = koin().get(NAMED_TOP_TV_SHOWS_REPOSITORY)
): Either<Throwable, List<TopRatedTVShowData>> = withUseCaseContext { repository(page) }
