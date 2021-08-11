package org.m0skit0.android.dabestmoviedbapp.data

data class TVShow(
    val imagePath: String,
    val name: String,
    val originalName: String,
    val voteAverage: Double,
    val overview: String,
    val genres: List<String>,
    val firstAirDate: String,
    val originCountry: String,
    val originalLanguage: String,
    val popularity: Double,
    val voteCount: Int,
)
