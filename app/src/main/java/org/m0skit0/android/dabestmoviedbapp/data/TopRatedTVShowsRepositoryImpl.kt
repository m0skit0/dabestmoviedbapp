package org.m0skit0.android.dabestmoviedbapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShow
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsService
import javax.inject.Inject

class TopRatedTVShowsRepositoryImpl
@Inject constructor(
    private val topRatedTVShowsService: TopRatedTVShowsService,
    private val tvGenreMapper: TVGenreMapper
) : TopRatedTVShowsRepository {

    override fun topRatedTVShows(): Flow<List<TVShow>> =
        topRatedTVShowsService.topRatedTVShows().map {
            it.topRatedTVShows.map { tvShow -> tvShow.toTVShow() }
        }

    private suspend fun TopRatedTVShow.toTVShow(): TVShow = TVShow(
        imagePath = posterPath,
        name = name,
        voteAverage = voteAverage,
        originalName = originalName,
        overview = overview,
        // TODO There's surely a better way to do this...
        genres = tvGenreMapper.mapGenres(genreIds).toList().first(),
        firstAirDate = firstAirDate,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        popularity = popularity,
        voteCount = voteCount,
    )
}
