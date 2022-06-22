package org.m0skit0.android.dabestmoviedbapp.state

import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsData

data class ShowDetailsState(
    val currentShowId: Long = -1L,
    val tvShowDetails: TVShowDetailsData? = null,
)

infix fun ShowDetailsState.updateTvShowDetailsWith(newShow: TVShowDetailsData): ShowDetailsState =
    copy(tvShowDetails = newShow)
