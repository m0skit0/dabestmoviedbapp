package org.m0skit0.android.dabestmoviedbapp.data.showdetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.m0skit0.android.dabestmoviedbapp.BuildConfig

@Parcelize
data class TVShowDetailsData(
    val id: Long,
    val genres: List<String>,
    val name: String,
    val posterPath: String,
    val overview: String,
    val voteAverage: String,
    val voteCount: String,
) : Parcelable

fun TVShowDetailsApi.toTVShowDetailsData() = TVShowDetailsData(
    id = id.toLong(),
    name = name,
    posterPath = posterPath?.toOriginalPosterFullUrl() ?: "",
    genres = genres.map { it.name },
    overview = overview,
    voteAverage = voteAverage.toString(), // TODO Format correctly
    voteCount = voteCount.toString(),
)

private fun String.toOriginalPosterFullUrl(): String =
    "${BuildConfig.IMAGE_BASE_URL}${BuildConfig.ORIGINAL_POSTER_SIZE}$this"
