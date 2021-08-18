package org.m0skit0.android.dabestmoviedbapp.domain.showdetails

import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsData

data class TVShowDetailsDomain(
    val id: Long,
    val genres: List<String>,
    val name: String,
    val posterPath: String,
    val overview: String,
    val voteAverage: String,
    val voteCount: String,
)

fun TVShowDetailsData.toTVShowDetailsDomain() = TVShowDetailsDomain(
    id = id,
    genres = genres,
    name = name,
    posterPath = posterPath,
    overview = overview,
    voteAverage = voteAverage,
    voteCount = voteCount,
)
