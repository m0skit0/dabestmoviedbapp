package org.m0skit0.android.dabestmoviedbapp.presentation.showdetails

data class TVShowDetailsView(
    val id: Long = -1,
    val poster: String = "",
    val title: String = "",
    val voteAverage: String = "",
    val voteCount: String = "",
    val genres: String = "",
    val overview: String = "",
)
