package org.m0skit0.android.dabestmoviedbapp.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsData

@Parcelize
data class ShowDetailsState(
    val currentShowId: Long = -1L,
    val tvShowDetails: TVShowDetailsData? = null,
) : ApplicationState(), Parcelable

infix fun ShowDetailsState.updateTvShowDetailsWith(newShow: TVShowDetailsData): ShowDetailsState =
    copy(tvShowDetails = newShow)
