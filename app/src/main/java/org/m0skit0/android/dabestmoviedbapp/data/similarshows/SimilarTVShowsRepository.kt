package org.m0skit0.android.dabestmoviedbapp.data.similarshows

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState

typealias SimilarTVShowsRepository = suspend (state: ApplicationState) -> Either<Throwable, SimilarTVShowsState>
typealias SimilarTVShowsState = Pair<ApplicationState, List<SimilarTVShowData>>

suspend fun similarTVShows(
    state: ApplicationState,
    similarTVShowsService: SimilarTVShowsService = koin().get()
): Either<Throwable, SimilarTVShowsState> = Either.catch {
    state to (state.currentSeries?.let { seriesId ->
        similarTVShowsService.similarTVShows(id = seriesId)
            .results
            .map { it.toSimilarTVShowsData() }
    } ?: emptyList())
}
