package org.m0skit0.android.dabestmoviedbapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowApi
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsService
import javax.inject.Inject

class TopRatedTVShowsRepositoryImpl
@Inject constructor(
    private val topRatedTVShowsService: TopRatedTVShowsService,
    private val tvGenreMapper: TVGenreMapper
) : TopRatedTVShowsRepository {

    override fun topRatedTVShows(): Flow<List<TVShowData>> =
        topRatedTVShowsService.topRatedTVShows().map {
            it.topRatedTVShows.map { tvShow -> tvShow.toTVShow() }
        }

    private suspend fun TopRatedTVShowApi.toTVShow(): TVShowData = TVShowData(
        id = id,
        imagePath = posterPath,
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
