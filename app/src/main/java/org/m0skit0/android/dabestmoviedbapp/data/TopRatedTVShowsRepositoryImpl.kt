package org.m0skit0.android.dabestmoviedbapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowApi
import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TopRatedTVShowsService
import javax.inject.Inject

class TopRatedTVShowsRepositoryImpl
@Inject constructor(
    private val topRatedTVShowsService: TopRatedTVShowsService,
    private val tvGenreMapper: TVGenreMapper
) : TopRatedTVShowsRepository {

    override fun topRatedTVShows(): Flow<List<TVShowData>> = flow {
        emit(
            topRatedTVShowsService
                .topRatedTVShows()
                .topRatedTVShows
                .map { tvShow -> tvShow.toTVShow() }
        )
    }.flowOn(Dispatchers.IO)

    private suspend fun TopRatedTVShowApi.toTVShow(): TVShowData = TVShowData(
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

    private fun String.toPreviewPosterFullUrl(): String =
        "${BuildConfig.IMAGE_BASE_URL}${BuildConfig.PREVIEW_POSTER_SIZE}$this"
}
