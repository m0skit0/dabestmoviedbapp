package org.m0skit0.android.dabestmoviedbapp.data

import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowApi
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsService
import javax.inject.Inject

class TopRatedTVShowsRepositoryImpl
@Inject constructor(
    private val topRatedTVShowsService: TopRatedTVShowsService,
    private val tvGenreMapper: TVGenreMapper
) : TopRatedTVShowsRepository {

    override suspend fun topRatedTVShows(page: Int): List<TopRatedTVShowData> =
        topRatedTVShowsService
            .topRatedTVShows(page = page)
            .topRatedTVShows
            .map { tvShow -> tvShow.toTVShow() }

    private suspend fun TopRatedTVShowApi.toTVShow(): TopRatedTVShowData = TopRatedTVShowData(
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
}
