package org.m0skit0.android.dabestmoviedbapp.data.toprated

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.TVGenresRepository
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.cacheTVGenres
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.state.ApplicationState

typealias TopRatedTVShowsRepository = suspend (state: ApplicationState) -> Either<Throwable, TopRatedTVShowsState>
typealias TopRatedTVShowsState = Pair<ApplicationState, List<TopRatedTVShowData>>

suspend fun topRatedTVShowsRepository(
    state: ApplicationState,
    topRatedTVShowsService: TopRatedTVShowsService = koin().get(),
    tvGenreMapper: TVGenresRepository = koin().get(),
): Either<Throwable, TopRatedTVShowsState> =
    Either.catch {
        topRatedTVShowsService
            .topRatedTVShows(page = state.currentPage)
            .topRatedTVShows
            .toTVShows(state, tvGenreMapper)
    }

private suspend fun List<TopRatedTVShowApi>.toTVShows(
    state: ApplicationState,
    tvGenreMapper: TVGenresRepository
): Pair<ApplicationState, List<TopRatedTVShowData>> =
    cacheTVGenres(state).let { newState ->
        newState to
                map {
                    TopRatedTVShowData(
                        id = it.id,
                        imagePath = it.posterPath?.toPreviewPosterFullUrl() ?: "",
                        name = it.name,
                        voteAverage = it.voteAverage,
                        originalName = it.originalName,
                        overview = it.overview,
                        genres = tvGenreMapper(newState, it.genreIds),
                        firstAirDate = it.firstAirDate,
                        originCountry = it.originCountry,
                        originalLanguage = it.originalLanguage,
                        popularity = it.popularity,
                        voteCount = it.voteCount,
                    )
                }
    }

private fun String.toPreviewPosterFullUrl(): String =
    "${BuildConfig.IMAGE_BASE_URL}${BuildConfig.PREVIEW_POSTER_SIZE}$this"
