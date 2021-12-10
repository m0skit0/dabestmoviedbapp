package org.m0skit0.android.dabestmoviedbapp.domain.toprated

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowsRepository
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.withUseCaseContext

suspend fun topTVShowsUseCase(
    page: Int,
    repository: TopRatedTVShowsRepository = koin().get()
): Either<Throwable, List<TopRatedTVShowData>> = withUseCaseContext { repository(page) }
