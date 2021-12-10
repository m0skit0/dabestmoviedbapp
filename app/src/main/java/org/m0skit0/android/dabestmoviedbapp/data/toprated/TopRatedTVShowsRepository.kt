package org.m0skit0.android.dabestmoviedbapp.data.toprated

import arrow.core.Either
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowApi
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsService
import org.m0skit0.android.dabestmoviedbapp.data.toPreviewPosterFullUrl
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.TVGenresRepository
import org.m0skit0.android.dabestmoviedbapp.di.koin

typealias TopRatedTVShowsRepository = suspend (page: Int) -> Either<Throwable, List<TopRatedTVShowData>>

suspend fun topRatedTVShowsRepository(
    topRatedTVShowsService: TopRatedTVShowsService = koin().get(),
    tvGenreMapper: TVGenresRepository = koin().get(),
    page: Int
): Either<Throwable, List<TopRatedTVShowData>> =
    Either.catch {
        topRatedTVShowsService
            .topRatedTVShows(page = page)
            .topRatedTVShows
            .map { tvShow -> tvShow.toTVShow(tvGenreMapper) }
    }

private suspend fun TopRatedTVShowApi.toTVShow(tvGenreMapper: suspend (ids: List<Int>) -> List<String>): TopRatedTVShowData =
    TopRatedTVShowData(
        id = id,
        imagePath = posterPath?.toPreviewPosterFullUrl() ?: "",
        name = name,
        voteAverage = voteAverage,
        originalName = originalName,
        overview = overview,
        genres = tvGenreMapper(genreIds),
        firstAirDate = firstAirDate,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        popularity = popularity,
        voteCount = voteCount,
    )
