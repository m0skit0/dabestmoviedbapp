package org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist

import org.m0skit0.android.dabestmoviedbapp.data.toprated.TopRatedTVShowData

data class TopRatedTVShowsItem(
    val id: Long,
    val poster: String,
    val title: String,
    val voteAverage: String,
)

fun TopRatedTVShowData.toTopRatedListingItem() = TopRatedTVShowsItem(
    id = id,
    poster = imagePath,
    title = name,
    voteAverage = voteAverage.toString(), // TODO Format correctly
)
