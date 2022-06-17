package org.m0skit0.android.dabestmoviedbapp.data.toprated

data class TopRatedTVShowData(
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
