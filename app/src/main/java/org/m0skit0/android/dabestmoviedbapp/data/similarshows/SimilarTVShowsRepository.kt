package org.m0skit0.android.dabestmoviedbapp.data.similarshows

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.state.SimilarShowsState
import org.m0skit0.android.dabestmoviedbapp.state.updateSimilarShowsWith

typealias SimilarTVShowsRepository = suspend (state: SimilarShowsState) -> Either<Throwable, SimilarShowsState>

suspend fun similarTVShows(
    state: SimilarShowsState,
    similarTVShowsService: SimilarTVShowsService = koin().get()
): Either<Throwable, SimilarShowsState> = Either.catch {
    state.currentShowId.let { seriesId ->
        similarTVShowsService.similarTVShows(id = seriesId)
            .results
            .map { it.toSimilarTVShowsData() }
    }.let { similarTVShows ->
        state updateSimilarShowsWith similarTVShows
    }
}
