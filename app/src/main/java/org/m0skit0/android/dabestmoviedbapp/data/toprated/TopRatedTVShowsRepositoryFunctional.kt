package org.m0skit0.android.dabestmoviedbapp.data.toprated

import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowApi
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsService
import org.m0skit0.android.dabestmoviedbapp.data.toPreviewPosterFullUrl
import org.m0skit0.android.dabestmoviedbapp.data.tvgenres.TVGenreMapper
import org.m0skit0.android.dabestmoviedbapp.di.koin

suspend fun topRatedTVShowsRepository(
    topRatedTVShowsService: TopRatedTVShowsService = koin().get(),
    tvGenreMapper: TVGenreMapper = koin().get(),
    page: Int
): List<TopRatedTVShowData> =
    topRatedTVShowsService
        .topRatedTVShows(page = page)
        .topRatedTVShows
        .map { tvShow -> tvShow.toTVShow(tvGenreMapper) }

private suspend fun TopRatedTVShowApi.toTVShow(tvGenreMapper: TVGenreMapper): TopRatedTVShowData =
    TopRatedTVShowData(
        id = id,
        imagePath = posterPath?.toPreviewPosterFullUrl() ?: "",
        name = name,
        voteAverage = voteAverage,
        originalName = originalName,
        overview = overview,
        genres = tvGenreMapper.mapGenres(genreIds),
        firstAirDate = firstAirDate,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        popularity = popularity,
        voteCount = voteCount,
    )
