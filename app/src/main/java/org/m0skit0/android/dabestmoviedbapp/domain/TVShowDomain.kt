package org.m0skit0.android.dabestmoviedbapp.domain

import org.m0skit0.android.dabestmoviedbapp.data.TVShowData

data class TVShowDomain(
    val id: Long,
    val imagePath: String,
    val name: String,
    val voteAverage: Double,
    val originalName: String,
    val overview: String,
    val genres: List<String>,
    val firstAirDate: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val popularity: Double,
    val voteCount: Int,
)

fun TVShowData.toTVShowDomain(): TVShowDomain = TVShowDomain(
    id = id,
    imagePath = imagePath,
    name = name,
    voteAverage = voteAverage,
    originalName = originalName,
    overview = overview,
    genres = genres,
    firstAirDate = firstAirDate,
    originCountry = originCountry,
    originalLanguage = originalLanguage,
    popularity = popularity,
    voteCount = voteCount,
)
