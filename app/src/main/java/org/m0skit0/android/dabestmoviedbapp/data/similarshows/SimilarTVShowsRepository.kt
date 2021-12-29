package org.m0skit0.android.dabestmoviedbapp.data.similarshows

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState

typealias SimilarTVShowsRepository = suspend (state: ApplicationState) -> Either<Throwable, SimilarTVShowsRepositoryState>
typealias SimilarTVShowsRepositoryState = Pair<ApplicationState, List<SimilarTVShowData>>

val SimilarTVShowsRepositoryState.applicationState: ApplicationState get() = first
val SimilarTVShowsRepositoryState.similarTVShowData: List<SimilarTVShowData> get() = second

suspend fun similarTVShows(
    state: ApplicationState,
    similarTVShowsService: SimilarTVShowsService = koin().get()
): Either<Throwable, SimilarTVShowsRepositoryState> = Either.catch {
    state to (state.showDetailsState.currentShowId.let { seriesId ->
        similarTVShowsService.similarTVShows(id = seriesId)
            .results
            .map { it.toSimilarTVShowsData() }
    })
}
