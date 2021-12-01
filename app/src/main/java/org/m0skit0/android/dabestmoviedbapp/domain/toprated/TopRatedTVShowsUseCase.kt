package org.m0skit0.android.dabestmoviedbapp.domain.toprated

import arrow.core.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TOP_RATED_TV_SHOWS_REPOSITORY
import org.m0skit0.android.dabestmoviedbapp.di.koin

suspend fun topTVShowsUseCase(
    page: Int,
    repository: suspend (page: Int) -> Either<Throwable, List<TopRatedTVShowData>> = koin().get(NAMED_TOP_RATED_TV_SHOWS_REPOSITORY)
): Either<Throwable, List<TopRatedTVShowData>> = withContext(Dispatchers.IO) { repository(page) }
