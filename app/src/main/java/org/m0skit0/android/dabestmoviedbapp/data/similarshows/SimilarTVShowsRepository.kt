package org.m0skit0.android.dabestmoviedbapp.data.similarshows

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState
import org.m0skit0.android.dabestmoviedbapp.state.updateSimilarShowsWith

typealias SimilarTVShowsRepository = suspend (state: ApplicationState) -> Either<Throwable, ApplicationState>

suspend fun similarTVShows(
    state: ApplicationState,
    similarTVShowsService: SimilarTVShowsService = koin().get()
): Either<Throwable, ApplicationState> = Either.catch {
    state.showDetailsState.currentShowId.let { seriesId ->
        similarTVShowsService.similarTVShows(id = seriesId)
            .results
            .map { it.toSimilarTVShowsData() }
    }.let { similarTVShows ->
        state updateSimilarShowsWith similarTVShows
    }
}
