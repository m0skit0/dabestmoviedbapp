package org.m0skit0.android.dabestmoviedbapp.data

import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVShowDetailsApi

data class TVShowDetailsData(
    val id: Long,
    val genres: List<String>,
    val name: String,
    val posterPath: String,
    val overview: String,
    val voteAverage: String,
    val voteCount: String,
)

fun TVShowDetailsApi.toTVShowDetailsData() = TVShowDetailsData(
    id = id.toLong(),
    name = name,
    posterPath = posterPath?.toOriginalPosterFullUrl() ?: "",
    genres = genres.map { it.name },
    overview = overview,
    voteAverage = voteAverage.toString(), // TODO Format correctly
    voteCount = voteCount.toString(),
)
