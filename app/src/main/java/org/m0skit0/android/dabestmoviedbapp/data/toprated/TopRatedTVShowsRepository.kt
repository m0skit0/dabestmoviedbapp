package org.m0skit0.android.dabestmoviedbapp.data.toprated

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.state.TopRatedState
import org.m0skit0.android.dabestmoviedbapp.state.updateGenreMappingCacheWith
import org.m0skit0.android.dabestmoviedbapp.state.updateTopRatedShowsWith

typealias TopRatedTVShowsRepository = suspend (state: TopRatedState) -> Either<Throwable, TopRatedState>

suspend fun topRatedTVShowsRepository(
    state: TopRatedState,
    topRatedTVShowsService: TopRatedTVShowsService = koin().get(),
    tvGenreService: () -> TVGenreService = { koin().get() },
): Either<Throwable, TopRatedState> =
    Either.catch {
        topRatedTVShowsService
            .topRatedTVShows(page = state.currentPage)
            .topRatedTVShows
            .toTVShows(state, tvGenreService)
    }

private suspend fun List<TopRatedTVShowDTO>.toTVShows(
    state: TopRatedState,
    tvGenreService: () -> TVGenreService,
): TopRatedState = cacheTVGenres(state, tvGenreService).let { newState ->
    newState updateTopRatedShowsWith map {
        TopRatedTVShowData(
            id = it.id,
            imagePath = it.posterPath?.toPreviewPosterFullUrl() ?: "",
            name = it.name,
            voteAverage = it.voteAverage,
            originalName = it.originalName,
            overview = it.overview,
            genres = it.genreIds.mapTVGenres(newState),
            firstAirDate = it.firstAirDate ?: "",
            originCountry = it.originCountry,
            originalLanguage = it.originalLanguage,
            popularity = it.popularity,
            voteCount = it.voteCount,
        )
    }
}

private fun List<Int>.mapTVGenres(
    state: TopRatedState,
): List<String> = mapNotNull { state.genreMappingCache.getOrDefault(it, null) }

private fun String.toPreviewPosterFullUrl(): String =
    "${BuildConfig.IMAGE_BASE_URL}${BuildConfig.PREVIEW_POSTER_SIZE}$this"

private suspend fun cacheTVGenres(
    state: TopRatedState,
    tvGenreService: () -> TVGenreService,
): TopRatedState =
    if (state.genreMappingCache.isEmpty())
        tvGenreService().tvGenres().toMap().let {
            state updateGenreMappingCacheWith it
        }
    else state

private fun TVGenresDTO.toMap(): Map<Int, String> = tVGenres.associate { it.id to it.name }
