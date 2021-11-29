package org.m0skit0.android.dabestmoviedbapp.domain.toprated

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData
import org.m0skit0.android.dabestmoviedbapp.data.toprated.topRatedTVShowsRepository

suspend fun topTVShowsUseCase(
    page: Int,
    provider: suspend (page: Int) -> List<TopRatedTVShowData> = { topRatedTVShowsRepository(page = it) }
): List<TopRatedTVShowData> =
    withContext(Dispatchers.IO) {
        provider(page)
    }
