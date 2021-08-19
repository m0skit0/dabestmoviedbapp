package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

import org.m0skit0.android.dabestmoviedbapp.domain.showdetails.TVShowDetailsDomain

data class TVShowDetailsPresentation(
    val id: Long = -1,
    val poster: String = "",
    val title: String = "",
    val voteAverage: String = "",
    val voteCount: String = "",
    val genres: String = "",
    val overview: String = "",
)

fun TVShowDetailsDomain.toTVShowDetailsPresentation() = TVShowDetailsPresentation(
    id = id,
    poster = posterPath,
    title = name,
    voteAverage = voteAverage,
    voteCount = "($voteCount)",
    genres = genres.joinToString(", "),
    overview = overview,
)

fun TVShowDetailsPresentation.isEmpty() = id == -1L
